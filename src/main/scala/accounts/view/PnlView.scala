package accounts.view

import accounts.core.view.{CellFactory, Style, View}
import accounts.viewmodel._

import scalafx.Includes._
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.control.TableColumn._
import scalafx.scene.layout.Region
import scalafx.stage.Stage

import PnlView._

object PnlView {
  val WindowWidth = 1200
  val WindowHeight = Region.USE_COMPUTED_SIZE

  val ColumnScaleFactor = 10
}

class PnlView(vm: PnlViewModel) extends View {

  val content = new Button {
    text = "P/L"
    padding = Insets(top = 0, bottom = 0, left = 0, right = 5)
    minWidth = 50
    onAction = handle {
      pnlWindow.show()
    }
  }

  val pnlTable = new TableView[PnlSummaryViewModel](vm.all) {
    columnResizePolicy = TableView.ConstrainedResizePolicy

    columns += new TableColumn[PnlSummaryViewModel, PnlSummaryName] {
      cellValueFactory = {
        _.value.name
      }
      cellFactory = CellFactory[PnlSummaryName](_.toString)
      sortable = false
      rowFactory = { _ =>
        new TableRow[PnlSummaryViewModel] {
          item.onChange { (_, _, newValue) =>
            if (Option(newValue).exists(_.isTotal)) style = Style.Bold
            else style = Style.Normal
          }
        }
      }
      maxWidth = 120 * ColumnScaleFactor
    }

    vm.categories.foreach { tc =>
      columns += new TableColumn[PnlSummaryViewModel, BigDecimal] {
        text = tc.shortString
        cellValueFactory = {
          _.value.profit(tc)
        }
        sortable = false
        cellFactory = CellFactory[BigDecimal](PnlViewModel.formatDecimal(_), alignment = Some(Pos.CenterRight))
        maxWidth = 100 * ColumnScaleFactor
      }
    }

    columns += new TableColumn[PnlSummaryViewModel, BigDecimal] {
      text = "Total"
      cellValueFactory = {
        _.value.totalProfit
      }
      sortable = false
      cellFactory = _ => {
        new TableCell[PnlSummaryViewModel, BigDecimal] {
          item.onChange { (_, _, newValue) =>
            text = Option(newValue).map(PnlViewModel.formatDecimal).getOrElse("")
            style = Style.Bold
            alignment = Pos.CenterRight
          }
        }
      }
      maxWidth = 100 * ColumnScaleFactor
    }
  }

  val pnlWindow = new Stage {
    scene = new Scene(width = WindowWidth, height = WindowHeight) {
      root = pnlTable
    }
  }
}
