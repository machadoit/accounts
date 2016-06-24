package accounts.core.view

import scala.language.implicitConversions
import scalafx.beans.binding.Bindings
import scalafx.beans.property.Property
import scalafx.scene.control.{TableCell, TableColumn, Tooltip}

trait View {
  implicit protected def toFunction[A, B](cf: CellFactory[B]): TableColumn[A, B] => TableCell[A, B] = _ => {
    new TableCell[A, B] {
      item.onChange { (_, _, newValue) =>
        text = if (newValue != null) cf.text(newValue) else ""
        cf.tooltip.foreach { ttFunc =>
          val newTooltip = for {
            v <- Option(newValue)
            ttString <- ttFunc(v)
          } yield Tooltip(ttString)
          tooltip = newTooltip.orNull
        }
        cf.alignment.foreach(alignment = _)
      }
    }
  }

  implicit class RichBinding[A](p1: Property[_, A])(implicit ev: Null <:< A) {
    def <==>(p2: Property[_, Option[A]]): Unit = {
      NullableToOptionBidirectionalBinding.bind(p1, p2)
    }

    def <==(p2: Property[_, Option[A]]): Unit = {
      val b = Bindings.createObjectBinding(() => p2.delegate.getValue.orNull, p2)
      p1 <== b
    }
  }

}
