package accounts.view

import accounts.core.view.ViewTest
import accounts.model.FiltersModel
import accounts.viewmodel.FiltersViewModel
import org.testfx.api.FxAssert.verifyThat
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar

import scalafx.scene.Parent
import scalafx.scene.control.Button
import scalafx.scene.input.KeyCode

class HeaderViewTest extends ViewTest with MockitoSugar {

  def filtersModel = new FiltersModel()
  def filtersVm = new FiltersViewModel(filtersModel)

  val addRecordView = mock[AddRecordView]

  def header = new HeaderView(filtersVm, addRecordView)

  override def rootNode: Parent = {
    when(addRecordView.button).thenReturn(new Button)

    header.content
  }

  "Header panel" when {
    "created" should {
      "be empty" in {
        verifyThat("#transactionCodeField", hasText(""))
        verifyThat("#transactionCategoryCombo", hasComboText("All Categories"))
        verifyThat("#transactionTypeCombo", hasComboText("All Types"))
      }
    }
  }

  "Transaction code field" when {

    def enterStringCode(s: String) = {
      clickOn("#transactionCodeField")
      write(s)
      push(KeyCode.Enter)
    }

    def enterCode(i: Int) = enterStringCode(i.toString)

    "a known type is entered" should {
      "update fields" in {
        enterCode(113)
        verifyThat("#transactionCodeField", hasText("113"))
        verifyThat("#transactionCategoryCombo", hasComboText("Food"))
        verifyThat("#transactionTypeCombo", hasComboText("Food: Fish"))
      }
    }

    "an unknown type is entered" should {
      "update fields" in {
        enterCode(234)
        verifyThat("#transactionCodeField", hasText("234"))
        verifyThat("#transactionCategoryCombo", hasComboText("Local Payment"))
        verifyThat("#transactionTypeCombo", hasComboText("Local Payment: 234"))
      }
    }

    "a known category is entered" should {
      "update fields" in {
        enterCode(3)
        verifyThat("#transactionCodeField", hasText("3"))
        verifyThat("#transactionCategoryCombo", hasComboText("Wages"))
        verifyThat("#transactionTypeCombo", hasComboText("All Types"))
      }
    }

    "an unknown category is entered" should {
      "leave combos unchanged" in {
        enterCode(54)
        verifyThat("#transactionCodeField", hasText("54"))
        verifyThat("#transactionCategoryCombo", hasComboText("All Categories"))
        verifyThat("#transactionTypeCombo", hasComboText("All Types"))
      }
    }

    "an unparseable category is entered" should {
      "leave fields unchanged" in {
        enterStringCode("foo")
        verifyThat("#transactionCodeField", hasText(""))
        verifyThat("#transactionCategoryCombo", hasComboText("All Categories"))
        verifyThat("#transactionTypeCombo", hasComboText("All Types"))
      }
    }
  }

}
