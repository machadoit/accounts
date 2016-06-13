package accounts.core.view

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

}
