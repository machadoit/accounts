package accounts.view

import javafx.scene.Parent

import accounts.model.FiltersModel
import accounts.record.{TransactionCategory, TransactionType}
import accounts.viewmodel.FiltersViewModel
import org.junit.Test
import org.loadui.testfx.GuiTest
import org.loadui.testfx.GuiTest._
import javafx.scene.control.{ComboBox, TextField}
import javafx.scene.input.{KeyCode, MouseButton}

import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.Assertions

import scalafx.scene.control.Button

class HeaderViewTest extends GuiTest with Assertions with TypeCheckedTripleEquals {

  def filtersModel = new FiltersModel()
  def filtersVm = new FiltersViewModel(filtersModel)

  def addRecordView = new AddRecordView {
    override def button: Button = new Button()
  }

  // Lazy, to ensure that the header is created (once) on the JavaFX thread
  lazy val header = new HeaderView(filtersVm, addRecordView)

  override def getRootNode: Parent = header.content

  @Test
  def testTransactionCodeEntry() = {
    val field = find[TextField]("#transactionCodeField")
    click(field, MouseButton.PRIMARY)
    `type`("113").push(KeyCode.ENTER)

    // Hacky sleep to avoid race condition
    Thread.sleep(100)

    val catCombo = find[ComboBox[Option[TransactionCategory]]]("#transactionCategoryCombo")
    assert(catCombo.getValue === Some(TransactionCategory.Food))
    val typeCombo = find[ComboBox[Option[TransactionType]]]("#transactionTypeCombo")
    assert(typeCombo.getValue === Some(TransactionType.Fish))
  }

}
