package accounts.view

import accounts.core.view.View
import accounts.viewmodel.ShellViewModel

import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.Platform
import scalafx.scene.Scene
import scalafx.scene.control.{Tab, TabPane}
import scalafx.scene.layout._

import ShellView._

object ShellView {
  val WindowWidth = Region.USE_COMPUTED_SIZE
  val WindowHeight = 800
}

class ShellView(vm: ShellViewModel) extends View {

  val addRecord = new AddRecordView(vm.addRecord)
  val standingOrders = new StandingOrdersView(vm.standingOrders)
  val header = new HeaderView(vm.filters, addRecord, standingOrders)

  val grid = new GridView(vm.grid)
  val pnl = new PnlView(vm.pnl)

  val footer = new FooterView(vm.totals)

  val stage = new PrimaryStage {
    title = "Cortijo Rosario Accounts"

    scene = new Scene(width = WindowWidth, height = WindowHeight) {
      root = new BorderPane {
        top = header.content
        center = new TabPane {
          tabs = Seq(
            new Tab {
              text = "Transactions"
              closable = false
              content = grid.content
            },
            new Tab {
              text = "P/L"
              closable = false
              content = pnl.pnlTable
            }
          )
        }
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
