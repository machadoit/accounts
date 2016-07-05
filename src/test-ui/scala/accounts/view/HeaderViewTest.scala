package accounts.view

import accounts.model.FiltersModel

import accounts.viewmodel.FiltersViewModel
import org.testfx.api.FxAssert.verifyThat

import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.input.KeyCode
import scalafx.stage.Stage

class HeaderViewTest extends ViewTest {

  def filtersModel = new FiltersModel()
  def filtersVm = new FiltersViewModel(filtersModel)

  //noinspection MutatorLikeMethodIsParameterless
  def addRecordView = new AddRecordView {
    override def button = new Button()
  }

  def header = new HeaderView(filtersVm, addRecordView)

  def scene = new Scene {
    root = header.content
  }

  override def start(stage: Stage): Unit = {
    stage.scene = scene
    stage.show()
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
