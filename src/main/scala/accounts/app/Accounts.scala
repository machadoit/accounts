package accounts.app

import java.io.File

import accounts.model.ShellModel
import accounts.record.repository.file.FileRecordRepository
import accounts.view.ShellView
import accounts.viewmodel.ShellViewModel

import scalafx.application.JFXApp

object Accounts extends JFXApp {
  val fileName = parameters.named.getOrElse("transfile",
    throw new IllegalArgumentException("Missing argument: --transfile"))
  val file = new File(fileName)
  val records = new FileRecordRepository(file)

  val model = new ShellModel(records)
  val viewModel = new ShellViewModel(model)
  val view = new ShellView(viewModel)

  stage = view.stage
}
