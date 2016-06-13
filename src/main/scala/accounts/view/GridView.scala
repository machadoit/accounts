package accounts.view

import java.time.LocalDate

import accounts.core.view.{CellFactory, View}
import accounts.record.{AccountType, IncomeType, TransactionCategory, TransactionType}
import accounts.viewmodel.{GridViewModel, RecordViewModel}

import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.control.TableColumn._
import scalafx.scene.layout._
import scalafx.util.StringConverter

object GridView {
  val WindowWidth = 1000
  val WindowHeight = 800
  val HeaderHeight = 100
  val GridHeight = WindowHeight - HeaderHeight
}

import GridView._

class GridView(vm: GridViewModel) extends View {
  val stage = new PrimaryStage {
    title = "Cortijo Rosario Accounts"

    scene = new Scene(width = WindowWidth, height = WindowHeight) {
      root = new GridPane {
        columnConstraints = Seq(
          new ColumnConstraints { percentWidth = 100 }
        )
        rowConstraints = Seq(
          new RowConstraints { prefHeight = HeaderHeight },
          new RowConstraints { prefHeight = GridHeight; vgrow = Priority.Always }
        )

        val textFilterField = new TextField {
          textFormatter = new TextFormatter(StringConverter[Option[Int]](
            Some(_).filter(!_.isEmpty).map(_.toInt),
            _.map(_.toString).getOrElse("")
          )) {
            value <==> vm.textFilter
          }
        }

        val transactionCategoryCombo = new ComboBox[Option[TransactionCategory]](vm.transactionCategoryFilters) {
          converter = StringConverter.toStringConverter {
            case None => "All"
            case Some(tc) => tc.displayString
          }
          value <==> vm.transactionCategoryFilter
        }

        val transactionTypeCombo = new ComboBox[Option[TransactionType]](vm.transactionTypeFilters) {
          converter = StringConverter.toStringConverter {
            case None => "All"
            case Some(tt) => tt.displayString
          }
          value <==> vm.transactionTypeFilter
        }

        val header = new HBox {
          children = Seq(
            textFilterField,
            transactionCategoryCombo,
            transactionTypeCombo
          )
        }
        add(header, columnIndex = 0, rowIndex = 0)

        val table = new TableView[RecordViewModel](vm.records) {
          columnResizePolicy = TableView.ConstrainedResizePolicy

          columns += new TableColumn[RecordViewModel, LocalDate] {
            text = "Date"
            cellValueFactory = { _.value.date }
            comparator = Ordering.fromLessThan(_.isBefore(_))
            cellFactory = CellFactory[LocalDate](RecordViewModel.formatDate(_))
            maxWidth = 120
          }
          columns += new TableColumn[RecordViewModel, Option[TransactionType]] {
            text = "Type"
            cellValueFactory = { _.value.transactionType }
            comparator = Ordering.by(_.map(_.displayString))
            cellFactory = CellFactory.optionalWithTooltip[TransactionType](
              _.displayString,
              TransactionType.toInt(_).toString
            )
            maxWidth = 300
          }
          columns += new TableColumn[RecordViewModel, String] {
            text = "Description"
            cellValueFactory = { _.value.description }
            maxWidth = 300
          }
          columns += new TableColumn[RecordViewModel, Option[IncomeType]] {
            text = "Income Type"
            cellValueFactory = { _.value.incomeType }
            cellFactory = CellFactory.optional[IncomeType](_.displayString)
            maxWidth = 150
          }
          columns += new TableColumn[RecordViewModel, Option[AccountType]] {
            text = "Account"
            cellValueFactory = { _.value.accountType }
            cellFactory = CellFactory.optional[AccountType](_.toString)
            maxWidth = 100
          }
          columns += new TableColumn[RecordViewModel, Option[Int]] {
            text = "Reference"
            cellValueFactory = { _.value.reference }
            comparator = Ordering[Option[Int]]
            cellFactory = CellFactory.optional[Int](_.toString)
            maxWidth = 100
          }
          columns += new TableColumn[RecordViewModel, BigDecimal] {
            text = "Credit"
            cellValueFactory = { _.value.credit }
            comparator = Ordering[BigDecimal]
            cellFactory = CellFactory[BigDecimal](RecordViewModel.formatDecimal(_))
            maxWidth = 100
          }
          columns += new TableColumn[RecordViewModel, BigDecimal] {
            text = "Debit"
            cellValueFactory = { _.value.debit }
            comparator = Ordering[BigDecimal]
            cellFactory = CellFactory[BigDecimal](RecordViewModel.formatDecimal(_))
            maxWidth = 100
          }
        }
        add(table, columnIndex = 0, rowIndex = 1)
      }
    }
  }

  // Hack to workaround dodgy column alignment issue
  stage.width = stage.width() + 200
  stage.sizeToScene()
  stage.resizable = false
  stage.resizable = true
  stage.delegate.setWidth(stage.width() + 200)
}
