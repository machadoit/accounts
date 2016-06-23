package accounts.view

import accounts.core.view.View
import accounts.viewmodel.ShellViewModel

import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.Platform
import scalafx.scene.Scene
import scalafx.scene.layout._

object ShellView {
  val WindowWidth = Region.USE_COMPUTED_SIZE
  val WindowHeight = 800
  val HeaderHeight = 100
  val GridHeight = WindowHeight - HeaderHeight
}

import ShellView._

class ShellView(vm: ShellViewModel) extends View {

  val header = new HeaderView(vm.filters)
  val grid = new GridView(vm.grid)
  val footer = new FooterView(vm.totals)

  val stage = new PrimaryStage {
    title = "Cortijo Rosario Accounts"

    scene = new Scene(width = WindowWidth, height = WindowHeight) {
      root = new BorderPane {
        top = header.content
        center = grid.content
        bottom = footer.content
      }
    }
  }

  Platform.runLater {
    // Workaround for grid column misalignment issue
    stage.width = stage.width() + 1
    // Set focus to the transaction code field
    header.textFilterField.requestFocus()
  }
}
