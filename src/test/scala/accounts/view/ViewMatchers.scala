package accounts.view

import javafx.scene.Node
import javafx.scene.control.{ComboBox, TextField}

import org.hamcrest.{BaseMatcher, Description, Matcher}
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.Assertions
import org.testfx.util.WaitForAsyncUtils

import scala.util.{Failure, Success, Try}

trait ViewMatchers extends Assertions with TypeCheckedTripleEquals {

  private def matcher[A](assertion: A => Unit) = new BaseMatcher[A] {
    private var result: Try[Unit] = Success(())

    override def matches(item: Any): Boolean = {
      // ensure all events have finished processing before checking assertions
      WaitForAsyncUtils.waitForFxEvents()
      // Hacky sleep
      Thread.sleep(1000)
      WaitForAsyncUtils.waitForFxEvents()
      result = Try(assertion(item.asInstanceOf[A]))
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
}

object ViewMatchers extends ViewMatchers
