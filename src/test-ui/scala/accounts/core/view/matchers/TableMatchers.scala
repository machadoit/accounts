package accounts.core.view.matchers

import javafx.scene.Parent
import javafx.scene.control.{TableCell, TableRow, TableView}

import scala.collection.JavaConverters._

trait TableMatchers extends MatchersBase {
  private def closestRowParent(n: Parent): Parent = {
    val children = n.getChildrenUnmodifiable.asScala
    if (children.size === 1) closestRowParent(children.head.asInstanceOf[Parent])
    else children(1).asInstanceOf[Parent]
  }

  private def rowChildren(n: Parent): Seq[TableRow[_]] = {
    val children = n.getChildrenUnmodifiable.asScala
    val typedChildren = children.collect { case t: TableRow[_] => t }
    if (typedChildren.isEmpty) rowChildren(children.head.asInstanceOf[Parent])
    else typedChildren.filter(_.getItem !== null)
  }

  private def rows(t: TableView[_]): Seq[TableRow[_]] = {
    val parent = closestRowParent(t)
    rowChildren(parent)
  }

  def hasCellText(column: Int, row: Int, expected: String) = matcher[TableView[_]] { t =>
    val tableRow = rows(t)(row)
    val cell = tableRow.getChildrenUnmodifiable.get(column).asInstanceOf[TableCell[_, _]]

    assert(cell.getText === expected)
  }

  def hasRowsSize(expected: Int) =  matcher[TableView[_]] { t =>
    assert(rows(t).size === expected)
  }
}
