package accounts.core.viewmodel

import com.typesafe.scalalogging.StrictLogging
import accounts.core.viewmodel.ViewModel.VmState

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.language.implicitConversions
import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer

object ViewModel extends StrictLogging {

  def displayString(rawString: String): String = rawString.replaceAll("(.)([A-Z](?=[a-z]))", "$1 $2")

  sealed trait Calculation {
    def refresh(): Unit
  }

  case class PropertyCalculation[A](property: ObjectProperty[A], calculation: () => A)
  extends Calculation {
    def refresh(): Unit = {
      property() = calculation()
    }
  }

  case class BufferCalculation[A](buffer: ObservableBuffer[A], calculation: () => Seq[A])
  extends Calculation {
    def refresh(): Unit = {
      buffer.delegate.setAll(calculation().asJava)
    }
  }

  object Property {
    def apply[A](value: A)(implicit vmState: VmState): ObjectProperty[A] = {
      val p = ObjectProperty(value)
      p.onChange {
        if (!vmState.updating) {
          logger.info(s"Property.onUiChange: $p")
          try {
            vmState.updating = true
            vmState.refresh()
          } finally {
            vmState.updating = false
          }
        } else {
          logger.info(s"Skipping Property.onUiChange: $p")
        }
      }
      p
    }
  }

  object Binding {
    def apply[A](to: => A)(from: A => Unit)(implicit vmState: VmState): ObjectProperty[A] = {
      val p = ObjectProperty(to)
      p.onChange {
        logger.info(s"Binding.onChange: $p (updating: ${vmState.updating})")
        val updating = vmState.updating
        try {
          vmState.updating = true
          from(p())
          if (!updating) vmState.refresh()
        } finally {
          if (!updating) vmState.updating = false
        }
      }
      p
    }
  }

  object CalculatedProperty {
    def apply[A](calculation: => A)(implicit vmState: VmState): ObjectProperty[A] = {
      val p = ObjectProperty(calculation)
      vmState.calculations += PropertyCalculation(p, () => calculation)
      p
    }
  }

  object CalculatedBuffer {
    def apply[A](calculation: => Seq[A])(implicit vmState: VmState): ObservableBuffer[A] = {
      val b = ObservableBuffer(calculation)
      vmState.calculations += BufferCalculation(b, () => calculation)
      b
    }
  }

  class VmState {
    var updating: Boolean = false
    val calculations = mutable.Buffer[Calculation]()

    private[ViewModel] def refresh(): Unit = {
      logger.info(s"Refreshing: $calculations")
      calculations.foreach(_.refresh())
    }

  }

  class RichProperty[A](p: ObjectProperty[A]) {
    def onUiChange(f: A => Unit)(implicit vmState: VmState): Unit = p.onChange { (_, _, value) =>
      if (!vmState.updating) {
        logger.info(s"RichProperty.onUiChange: $p")
        try {
          vmState.updating = true
          f(value)
          vmState.refresh()
        } finally {
          vmState.updating = false
        }
      } else {
        logger.info(s"Skipping RichProperty.onUiChange: $p")
      }
    }

    def onUiChange(f: => Unit)(implicit vmState: VmState): Unit = onUiChange(_ => f)

  }

  implicit def toRichProperty[A](p: ObjectProperty[A]): RichProperty[A] = new RichProperty(p)

  val singletonVmState = new VmState
}

trait ViewModel {
  implicit protected val vmState = ViewModel.singletonVmState
}
