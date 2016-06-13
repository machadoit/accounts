package accounts.app

import java.io.File

import accounts.model.GridModel
import accounts.record.repository.file.FileRecordRepository
import accounts.view.GridView
import accounts.viewmodel.GridViewModel

import scalafx.application.JFXApp

object Accounts extends JFXApp {
  val fileName = parameters.named.getOrElse("transfile",
    throw new IllegalArgumentException("Missing argument: --transfile"))
  val file = new File(fileName)
  val records = new FileRecordRepository(file)

  val model = new GridModel(records)
  val viewModel = new GridViewModel(model)
  val view = new GridView(viewModel)

  stage = view.stage
}
