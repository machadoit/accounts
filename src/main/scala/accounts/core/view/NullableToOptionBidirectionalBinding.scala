package accounts.core.view

import scala.ref.WeakReference
import javafx.beans.property.Property
import javafx.beans.value.{ChangeListener, ObservableValue}

import com.sun.javafx.binding.BidirectionalBinding

import scala.util.control.NonFatal

object NullableToOptionBidirectionalBinding {
  def bind[A](property1: Property[A], property2: Property[Option[A]])(implicit ev: Null <:< A) = {
    val binding = new NullableToOptionBidirectionalBinding(property1, property2)
    property1.setValue(property2.getValue.orNull)
    property1.addListener(binding.bindingFrom1To2)
    property2.addListener(binding.bindingFrom2To1)
  }
}

private class NullableToOptionBidirectionalBinding[A] private(p1: Property[A], p2: Property[Option[A]])(implicit ev: Null <:< A) {
  private val propertyRef1: WeakReference[Property[A]] = WeakReference(p1)
  private val propertyRef2: WeakReference[Property[Option[A]]] = WeakReference(p2)
  private var updating: Boolean = false

  trait BindingBase[B] extends ChangeListener[B] {

    protected def update(property1: Property[A], property2: Property[Option[A]], value: B): Unit

    override def changed(observable: ObservableValue[_ <: B], oldValue: B, newValue: B): Unit = {
      if (!updating) {
        (propertyRef1, propertyRef2) match {
          case (WeakReference(property1), WeakReference(property2)) =>
            try {
              updating = true
              update(property1, property2, newValue)
            } catch {
              case NonFatal(e) =>
                try {
                  update(property1, property2, oldValue)
                } catch {
                  case NonFatal(e2) =>
                    e2.addSuppressed(e)
                    property1.removeListener(bindingFrom1To2)
                    property2.removeListener(bindingFrom2To1)
                    throw new RuntimeException("Bidirectional binding failed together with an attempt" +
                      " to restore the source property to the previous value." +
                      " Removing the bidirectional binding from properties " + property1 + " and " + property2, e2)
                }
                throw new RuntimeException("Bidirectional binding failed, setting to the previous value", e)
            } finally {
              updating = false
            }
          case (WeakReference(property1), _) =>
            property1.removeListener(bindingFrom1To2)
          case (_, WeakReference(property2)) =>
            property2.removeListener(bindingFrom2To1)
          case _ =>
        }
      }
    }
  }

  object bindingFrom1To2 extends BindingBase[A] {
    override protected def update(property1: Property[A], property2: Property[Option[A]], value: A): Unit =
      property2.setValue(Option(value))
  }

  object bindingFrom2To1 extends BindingBase[Option[A]] {
    override protected def update(property1: Property[A], property2: Property[Option[A]], value: Option[A]): Unit =
      property1.setValue(value.orNull)
  }

}
