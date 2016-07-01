package accounts.view

import java.time.Month

import accounts.core.view.{CellFactory, View}
import accounts.record.{AccountType, TransactionType}
import accounts.viewmodel.{StandingOrderViewModel, StandingOrdersViewModel}
import StandingOrdersView._

import scalafx.Includes._
import scalafx.application.Platform
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.control.TableColumn._
import scalafx.scene.layout.{BorderPane, HBox, Priority}
import scalafx.stage.Stage
import scalafx.util.StringConverter

object StandingOrdersView {
  val ColumnScaleFactor = 10
}

class StandingOrdersView(vm: StandingOrdersViewModel) extends View {

  val button = new Button {
    text = "Standing Orders"
    onAction = handle {
      window.show()
    }
  }

  val table = new TableView[StandingOrderViewModel](vm.all) {
    columnResizePolicy = TableView.ConstrainedResizePolicy

    columns += new TableColumn[StandingOrderViewModel, Int] {
      text = "Day"
      cellValueFactory = {
        _.value.dayOfMonth
      }
      comparator = Ordering[Int]
      cellFactory = CellFactory[Int](_.toString)
      maxWidth = 100 * ColumnScaleFactor
    }
    columns += new TableColumn[StandingOrderViewModel, Option[Month]] {
      text = "Month"
      cellValueFactory = {
        _.value.nextMonth
      }
      comparator = Ordering[Option[Month]]
      cellFactory = CellFactory[Option[Month]](View.optionMonthConverter.toString)
      maxWidth = 100 * ColumnScaleFactor
    }
    columns += new TableColumn[StandingOrderViewModel, Int] {
      text = "Interval"
      cellValueFactory = {
        _.value.dayOfMonth
      }
      comparator = Ordering[Int]
      cellFactory = CellFactory[Int](_.toString)
      maxWidth = 100 * ColumnScaleFactor
    }
    columns += new TableColumn[StandingOrderViewModel, TransactionType] {
      text = "Transaction Type"
      cellValueFactory = {
        _.value.transactionType
      }
      comparator = Ordering.by(_.displayString)
      cellFactory = CellFactory[TransactionType](
        _.displayString,
        Some(tt => Some(s"Code: ${tt.value}"))
      )
      maxWidth = 300 * ColumnScaleFactor
    }
    columns += new TableColumn[StandingOrderViewModel, BigDecimal] {
      text = "Debit"
      cellValueFactory = {
        _.value.debit
      }
      comparator = Ordering[BigDecimal]
      cellFactory = CellFactory[BigDecimal](View.formatDecimal, alignment = Some(Pos.CenterRight))
      maxWidth = 100 * ColumnScaleFactor
    }
    columns += new TableColumn[StandingOrderViewModel, AccountType] {
      text = "Account"
      cellValueFactory = {
        _.value.accountType
      }
      cellFactory = CellFactory[AccountType](_.toString)
      maxWidth = 100 * ColumnScaleFactor
    }
    columns += new TableColumn[StandingOrderViewModel, String] {
      text = "Description"
      cellValueFactory = {
        _.value.description
      }
      maxWidth = 300 * ColumnScaleFactor
    }
  }

  val window = new Stage {
    title = "Standing Orders"
    scene = new Scene {
      root = new BorderPane {
        center = table

        bottom = new HBox {
          padding = Insets(top = 0, bottom = 0, left = 10, right = 10)
          children = Seq(
            new HBox {
              padding = Insets(5)
              spacing = 5
              hgrow = Priority.Always
              alignment = Pos.CenterLeft
              children = Seq(
                new ComboBox[Option[Month]](None +: Month.values.map(Some(_))) {
                  editable = true
                  promptText = "Month"
                  prefWidth = 100
                  converter = View.optionMonthConverter
                  // Workaround for https://bugs.openjdk.java.net/browse/JDK-8129400
                  focused.onChange { (_, _, focusGained) =>
                    if (focusGained) {
                      Platform.runLater {
                        editor().selectAll()
                      }
                    }
                  }
                  value <==> vm.applyMonth
                },
                new TextField {
                  promptText = "Year"
                  prefWidth = 50
                  textFormatter = new TextFormatter(StringConverter[Option[Int]](
                    Option(_).filter(!_.isEmpty).map { s =>
                      val i = s.toInt
                      if (i > 100) i else i + 2000
                    },
                    _.map(_.toString).getOrElse("")
                  )) {
                    value <==> vm.applyYear
                  }
                },
                new Button {
                  text = "Apply"
                  maxWidth = Int.MaxValue
                }
              )
            },
            new HBox {
              padding = Insets(5)
              spacing = 5
              hgrow = Priority.Always
              alignment = Pos.CenterRight
              children = Seq(
                new Button {
                  text = "Add"
                  maxWidth = Int.MaxValue
                },
                new Button {
                  text = "Update"
                  maxWidth = Int.MaxValue
                },
                new Button {
                  text = "Delete"
                  maxWidth = Int.MaxValue
                  onAction = handle {
                    val toDelete = table.selectionModel().getSelectedItems()
                    vm.delete(toDelete)
                  }
                }
              )
            }
          )
        }
      }
    }
  }
}
