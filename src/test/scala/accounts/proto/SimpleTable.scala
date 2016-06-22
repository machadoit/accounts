package accounts.proto

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer
import scalafx.scene.Scene
import scalafx.scene.control.{TableColumn, TableView}
import scalafx.scene.control.TableColumn._
import scalafx.scene.layout._

class Record(_firstName: String, _lastName: String, _age: Int) {
  val firstName = ObjectProperty(_firstName)
  val lastName = ObjectProperty(_lastName)
  val age = ObjectProperty(_age)
}

object SimpleTable extends JFXApp {

  val records = ObservableBuffer(
    new Record("Fred", "Flintstone", 32),
    new Record("Barney", "Rubble", 29)
  )

  stage = new PrimaryStage {
    scene = new Scene(width = 1000, height = 800) {
      root = new GridPane {
        rowConstraints = Seq(
          new RowConstraints { prefHeight = 100 },
          new RowConstraints { prefHeight = 700; vgrow = Priority.Always }
        )
        columnConstraints = Seq(
          new ColumnConstraints { percentWidth = 100 }
        )

        add(new HBox {}, 0, 0)

        add(new TableView[Record](records) {

          columnResizePolicy = TableView.ConstrainedResizePolicy

          columns += new TableColumn[Record, String] {
            text = "First name"
            cellValueFactory = {
              _.value.firstName
            }
          }
          columns += new TableColumn[Record, String] {
            text = "Last name"
            cellValueFactory = {
              _.value.lastName
            }
          }
          columns += new TableColumn[Record, Int] {
            text = "Age"
            cellValueFactory = {
              _.value.age
            }
          }
        }, rowIndex = 1, columnIndex = 0)
      }
    }
  }
}
