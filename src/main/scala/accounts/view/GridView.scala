package accounts.view

import java.time.{LocalDate, Month}

import accounts.core.view.{CellFactory, View}
import accounts.record.{AccountType, IncomeType, TransactionCategory, TransactionType}
import accounts.viewmodel.{GridViewModel, RecordViewModel}

import scalafx.Includes._
import scalafx.application.JFXApp.PrimaryStage
import scalafx.application.Platform
import scalafx.geometry.{Insets, Orientation, Pos}
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.control.TableColumn._
import scalafx.scene.layout._
import scalafx.util.StringConverter

object GridView {
  val WindowWidth = 1200
  val WindowHeight = 800
  val HeaderHeight = 100
  val GridHeight = WindowHeight - HeaderHeight
  val ColumnScaleFactor = 0.9

  val NumericMonthRegex = "([0-9]{1,2})".r
}

import GridView._

class GridView(vm: GridViewModel) extends View {

  val textFilterField = new TextField {
    promptText = "Code"
    textFormatter = new TextFormatter(StringConverter[Option[Int]](
      Some(_).filter(!_.isEmpty).map(_.toInt),
      _.map(_.toString).getOrElse("")
    )) {
      value <==> vm.textFilter
    }
  }

  val stage = new PrimaryStage {
    title = "Cortijo Rosario Accounts"

    scene = new Scene(width = WindowWidth, height = WindowHeight) {
      root = new BorderPane {

        top = new HBox {
          children = Seq(
            new GridPane {
              padding = Insets(top = 5, bottom = 5, left = 15, right = 20)
              hgap = 5
              vgap = 5

              add(new Label {
                text = "Filters"
                style = "-fx-font-size: 16pt"
              }, columnIndex = 0, rowIndex = 0, colspan = 2, rowspan = 1)

              add(new Label {
                text = "Transaction type:"
                padding = Insets(top = 0, bottom = 0, left = 0, right = 5)
              }, columnIndex = 0, rowIndex = 1)

              add(new HBox {
                spacing = 5
                children = Seq(
                  new ComboBox[Option[TransactionCategory]](vm.transactionCategoryFilters) {
                    converter = StringConverter.toStringConverter {
                      case None => "All Categories"
                      case Some(tc) => tc.displayString
                    }
                    value <==> vm.transactionCategoryFilter
                  },
                  new ComboBox[Option[TransactionType]](vm.transactionTypeFilters) {
                    converter = StringConverter.toStringConverter {
                      case None => "All Types"
                      case Some(tt) => tt.displayString
                    }
                    value <==> vm.transactionTypeFilter
                  }
                )
              }, columnIndex = 1, rowIndex = 1)

              add(new Label {
                      text = "Date:"
                      padding = Insets(top = 0, bottom = 0, left = 0, right = 5)
                    }, columnIndex = 0, rowIndex = 2)

              add(new HBox {
                spacing = 5
                children = Seq(
                    new DatePicker {
                      promptText = "Start Date"
                      // Workaround for https://bugs.openjdk.java.net/browse/JDK-8129400
                      focused.onChange { (_, _, focusGained) =>
                        if (focusGained) {
                          Platform.runLater {
                            editor().selectAll()
                          }
                        }
                      }
                      value <==> vm.startDateFilter
                    },
                    new DatePicker {
                      promptText = "End Date"
                      // Workaround for https://bugs.openjdk.java.net/browse/JDK-8129400
                      focused.onChange { (_, _, focusGained) =>
                        if (focusGained) {
                          Platform.runLater {
                            editor().selectAll()
                          }
                        }
                      }
                      value <==> vm.endDateFilter
                    }
                  )
                }, columnIndex = 1, rowIndex = 2)

            },
            new Separator {
              orientation = Orientation.Vertical
              padding = Insets(top = 10, bottom = 10, left = 10, right = 10)
            },
            new GridPane {
              padding = Insets(5)
              hgap = 5
              vgap = 5

              add(new Label {
                text = "Quick Filters"
                style = "-fx-font-size: 16pt"
              }, columnIndex = 0, rowIndex = 0, colspan = 2, rowspan = 1)


              add(new Label {
                text = "Transaction type:"
                padding = Insets(top = 0, bottom = 0, left = 0, right = 5)
              }, columnIndex = 0, rowIndex = 1)

              add(new HBox {
                spacing = 5
                children = Seq(textFilterField)
              }, columnIndex = 1, rowIndex = 1)

              add(new Label {
                text = "Date:"
                padding = Insets(top = 0, bottom = 0, left = 0, right = 5)
              }, columnIndex = 0, rowIndex = 2)

              add(new HBox {
                spacing = 5
                children = Seq(
                  new ComboBox[Option[Month]](vm.monthFilters) {
                    editable = true
                    promptText = "Month"
                    converter = StringConverter[Option[Month]]({
                      Some(_).filter(s => !s.isEmpty && !s.equalsIgnoreCase("all")).map {
                        case NumericMonthRegex(s) => Month.of(s.toInt)
                        case s => Month.valueOf(s.toUpperCase)
                      }
                    },
                      {
                        case None => ""
                        case Some(m) => m.toString.toLowerCase.capitalize
                      })
                    // Workaround for https://bugs.openjdk.java.net/browse/JDK-8129400
                    focused.onChange { (_, _, focusGained) =>
                      if (focusGained) {
                        Platform.runLater {
                          editor().selectAll()
                        }
                      }
                    }
                    value <==> vm.monthFilter
                  },
                  new TextField {
                    promptText = "Year"
                    textFormatter = new TextFormatter(StringConverter[Option[Int]](
                      Some(_).filter(!_.isEmpty).map(_.toInt),
                      _.map(_.toString).getOrElse("")
                    )) {
                      value <==> vm.yearFilter
                    }
                  }
                )
              }, columnIndex = 1, rowIndex = 2)

            }
          )
        }

        center = new TableView[RecordViewModel](vm.records) {
          columnResizePolicy = TableView.ConstrainedResizePolicy

          columns += new TableColumn[RecordViewModel, LocalDate] {
            text = "Date"
            cellValueFactory = { _.value.date }
            comparator = Ordering.fromLessThan(_.isBefore(_))
            cellFactory = CellFactory[LocalDate](RecordViewModel.formatDate(_))
            maxWidth = 120 * ColumnScaleFactor
          }
          columns += new TableColumn[RecordViewModel, Option[Int]] {
            text = "Reference"
            cellValueFactory = { _.value.reference }
            comparator = Ordering[Option[Int]]
            cellFactory = CellFactory.optional[Int](_.toString)
            maxWidth = 100 * ColumnScaleFactor
          }
          columns += new TableColumn[RecordViewModel, Option[TransactionType]] {
            text = "Transaction Type"
            cellValueFactory = { _.value.transactionType }
            comparator = Ordering.by(_.map(_.displayString))
            cellFactory = CellFactory.optionalWithTooltip[TransactionType](
              _.displayString,
              tt => s"Code: ${TransactionType.toInt(tt)}"
            )
            maxWidth = 300 * ColumnScaleFactor
          }
          columns += new TableColumn[RecordViewModel, Option[IncomeType]] {
            text = "Income Type"
            cellValueFactory = { _.value.incomeType }
            cellFactory = CellFactory.optional[IncomeType](_.displayString)
            maxWidth = 150 * ColumnScaleFactor
          }
          columns += new TableColumn[RecordViewModel, BigDecimal] {
            text = "Credit"
            cellValueFactory = { _.value.credit }
            comparator = Ordering[BigDecimal]
            cellFactory = CellFactory[BigDecimal](RecordViewModel.formatDecimal(_))
            maxWidth = 100 * ColumnScaleFactor
          }
          columns += new TableColumn[RecordViewModel, BigDecimal] {
            text = "Debit"
            cellValueFactory = { _.value.debit }
            comparator = Ordering[BigDecimal]
            cellFactory = CellFactory[BigDecimal](RecordViewModel.formatDecimal(_))
            maxWidth = 100 * ColumnScaleFactor
          }
          columns += new TableColumn[RecordViewModel, Option[AccountType]] {
            text = "Account"
            cellValueFactory = { _.value.accountType }
            cellFactory = CellFactory.optional[AccountType](_.toString)
            maxWidth = 100 * ColumnScaleFactor
          }
          columns += new TableColumn[RecordViewModel, String] {
            text = "Description"
            cellValueFactory = { _.value.description }
            maxWidth = 300 * ColumnScaleFactor
          }
        }

        bottom = new GridPane {
          padding = Insets(5)
          hgap = 5
          vgap = 5

          add(new Label {
            text = "Totals"
            style = "-fx-font-size: 16pt"
          }, columnIndex = 0, rowIndex = 0, colspan = 3, rowspan = 1)

          add(new Label {
            text = "Period"
            style = "-fx-font-size: 12pt"
          }, columnIndex = 0, rowIndex = 1)

          add(new HBox {
            spacing = 5
            alignment = Pos.CenterLeft
            children = Seq(
              new Label {
                text = "Credit:"
              },
              new TextField {
                alignment = Pos.CenterRight
                editable = false
                textFormatter = new TextFormatter(StringConverter.toStringConverter[BigDecimal](
                  RecordViewModel.formatDecimal(_)
                )) {
                  value <== vm.totals.periodCredit
                }
              }
            )
          }, columnIndex = 1, rowIndex = 1)

          add(new HBox {
            spacing = 5
            alignment = Pos.CenterLeft
            children = Seq(
              new Label {
                text = "Debit:"
              },
              new TextField {
                alignment = Pos.CenterRight
                editable = false
                textFormatter = new TextFormatter(StringConverter.toStringConverter[BigDecimal](
                  RecordViewModel.formatDecimal(_)
                )) {
                  value <== vm.totals.periodDebit
                }
              }
            )
          }, columnIndex = 2, rowIndex = 1)

          add(new Label {
            text = "Brought Forward"
            style = "-fx-font-size: 12pt"
          }, columnIndex = 0, rowIndex = 2)

          add(new HBox {
            spacing = 5
            alignment = Pos.CenterLeft
            children = Seq(
              new Label {
                text = "Credit:"
              },
              new TextField {
                alignment = Pos.CenterRight
                editable = false
                textFormatter = new TextFormatter(StringConverter.toStringConverter[BigDecimal](
                  RecordViewModel.formatDecimal(_)
                )) {
                  value <== vm.totals.broughtForwardCredit
                }
              }
            )
          }, columnIndex = 1, rowIndex = 2)

          add(new HBox {
            spacing = 5
            alignment = Pos.CenterLeft
            children = Seq(
              new Label {
                text = "Debit:"
              },
              new TextField {
                alignment = Pos.CenterRight
                editable = false
                textFormatter = new TextFormatter(StringConverter.toStringConverter[BigDecimal](
                  RecordViewModel.formatDecimal(_)
                )) {
                  value <== vm.totals.broughtForwardDebit
                }
              }
            )
          }, columnIndex = 2, rowIndex = 2)

          add(new Label {
            text = "Year To Date"
            style = "-fx-font-size: 12pt"
          }, columnIndex = 0, rowIndex = 3)

          add(new HBox {
            spacing = 5
            alignment = Pos.CenterLeft
            children = Seq(
              new Label {
                text = "Credit:"
              },
              new TextField {
                alignment = Pos.CenterRight
                editable = false
                textFormatter = new TextFormatter(StringConverter.toStringConverter[BigDecimal](
                  RecordViewModel.formatDecimal(_)
                )) {
                  value <== vm.totals.toDateCredit
                }
              }
            )
          }, columnIndex = 1, rowIndex = 3)

          add(new HBox {
            spacing = 5
            alignment = Pos.CenterLeft
            children = Seq(
              new Label {
                text = "Debit:"
              },
              new TextField {
                alignment = Pos.CenterRight
                editable = false
                textFormatter = new TextFormatter(StringConverter.toStringConverter[BigDecimal](
                  RecordViewModel.formatDecimal(_)
                )) {
                  value <== vm.totals.toDateDebit
                }
              }
            )
          }, columnIndex = 2, rowIndex = 3)
        }
      }
    }
  }

  Platform.runLater {
    textFilterField.requestFocus()
  }
}
