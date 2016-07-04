package accounts.view

import accounts.model.FiltersModel

import accounts.viewmodel.FiltersViewModel
import org.scalactic.TypeCheckedTripleEquals
import org.testfx.api.FxAssert.verifyThat
import org.testfx.matcher.base.NodeMatchers.hasText

import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.input.KeyCode
import scalafx.stage.Stage

class HeaderViewTest extends ViewTest with TypeCheckedTripleEquals {

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

  "Transaction code field" when {

    "created" should {
      "be empty" in {
        verifyThat("#transactionCodeField", hasText(""))
        verifyThat("#transactionCategoryCombo", hasComboText("All Categories"))
        verifyThat("#transactionTypeCombo", hasComboText("All Types"))
      }
    }

    "a known code is entered" should {
      def setup() = {
        clickOn("#transactionCodeField")
        write("113")
        push(KeyCode.Enter)
      }

      "update combos" in {
        setup()
        verifyThat("#transactionCategoryCombo", hasComboText("Food"))
        verifyThat("#transactionTypeCombo", hasComboText("Food: Fish"))
      }
    }
  }

}
