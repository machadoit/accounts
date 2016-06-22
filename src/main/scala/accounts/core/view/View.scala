package accounts.core.view

import scala.language.implicitConversions
import scalafx.beans.property.Property
import scalafx.scene.control.{TableCell, TableColumn, Tooltip}

trait View {
  implicit protected def toFunction[A, B](cf: CellFactory[B]): TableColumn[A, B] => TableCell[A, B] = _ => {
    new TableCell[A, B] {
      item.onChange { (_, _, newValue) =>
        text() = if (newValue != null) cf.text(newValue) else ""
        tooltip() = Option(newValue).flatMap(cf.tooltip).map(Tooltip(_).delegate).orNull
      }
    }
  }

  implicit class RichBinding[A](p1: Property[_, A])(implicit ev: Null <:< A) {
    def <==>(p2: Property[_, Option[A]]): Unit = {
      NullableToOptionBidirectionalBinding.bind(p1, p2)
    }
  }

}
