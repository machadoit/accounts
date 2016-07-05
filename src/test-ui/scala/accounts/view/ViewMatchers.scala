package accounts.view

import javafx.scene.{Node, Parent}
import javafx.scene.control._

import org.hamcrest.{BaseMatcher, Description, Matcher}
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.Assertions
import org.testfx.util.WaitForAsyncUtils

import scala.collection.JavaConverters._
import scala.util.{Failure, Success, Try}

trait ViewMatchers extends Assertions with TypeCheckedTripleEquals {

  private def matcher[A](assertion: A => Unit) = new BaseMatcher[A] {
    private var result: Try[Unit] = Success(())

    override def matches(item: Any): Boolean = {
      // ensure all events have finished processing before checking assertions
      result = WaitForAsyncUtils.waitForAsyncFx(60000, () => Try(assertion(item.asInstanceOf[A])))
      result.isSuccess
    }

    override def describeTo(description: Description): Unit = description.appendText("Success")

    override def describeMismatch(item: Any, description: Description): Unit = result match {
      case Failure(e) => description.appendText(e.getMessage)
      case _ => // do nothing
    }
  }

  private def comboText[A](combo: ComboBox[A]): String = combo.getConverter.toString(combo.getValue)

  def hasComboText[A](expected: String): Matcher[ComboBox[A]] = matcher[ComboBox[A]] { cb =>
    assert(comboText(cb) === expected)
  }

  private def text(n: Node): String = n match {
    case t: TextField => t.getText
  }

  def hasText(expected: String): Matcher[TextField] = matcher[TextField] { tf =>
    assert(text(tf) === expected)
  }

  private def rows(t: TableView[_]): Seq[TableRow[_]] = {
    var current = t.getChildrenUnmodifiable
    while (current.size === 1) current = current.get(0).asInstanceOf[Parent].getChildrenUnmodifiable
    current = current.get(1).asInstanceOf[Parent].getChildrenUnmodifiable
    while (!current.get(0).isInstanceOf[TableRow[_]]) current = current.get(0).asInstanceOf[Parent].getChildrenUnmodifiable
    current.asScala.map(_.asInstanceOf[TableRow[_]]).filter(_.getItem !== null)
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

object ViewMatchers extends ViewMatchers
