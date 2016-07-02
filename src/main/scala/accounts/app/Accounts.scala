package accounts.app

import java.io.File

import accounts.model.ShellModel
import accounts.record.repository.file.FileRecordRepository
import accounts.view.ShellView
import accounts.viewmodel.ShellViewModel

import scalafx.application.JFXApp

object Accounts extends JFXApp {
  val transFileName = parameters.named.getOrElse("transfile",
    throw new IllegalArgumentException("Missing argument: --transfile"))
  val transFile = new File(transFileName)
  val recordRepo = new FileRecordRepository(transFile)

  val model = new ShellModel(recordRepo)
  val viewModel = new ShellViewModel(model)
  val view = new ShellView(viewModel)

  stage = view.stage
}
