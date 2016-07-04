package accounts.view

import javafx.scene.control.ComboBox

import org.hamcrest.{BaseMatcher, Description, Matcher}
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.Assertions

import scala.util.{Failure, Success, Try}

trait ViewMatchers extends Assertions with TypeCheckedTripleEquals {

  def comboText[A](combo: ComboBox[A]): String = combo.getConverter.toString(combo.getValue)

  def hasComboText[A](expected: String): Matcher[ComboBox[A]] = new BaseMatcher[ComboBox[A]] {
    private var result: Try[Unit] = Success(())

    override def matches(item: Any): Boolean = {
      result = Try(assert(comboText(item.asInstanceOf[ComboBox[A]]) === expected))
      result.isSuccess
    }

    override def describeTo(description: Description): Unit = result match {
      case Failure(e) => description.appendText(e.getMessage)
      case _ => // do nothing
    }
  }
}

object ViewMatchers extends ViewMatchers
