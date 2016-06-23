package accounts.viewmodel

import java.time.{LocalDate, Month}

import accounts.core.viewmodel.ViewModel
import accounts.core.viewmodel.ViewModel._
import accounts.model.GridModel
import accounts.record.{AccountType, TransactionCategory, TransactionType}

import scalafx.collections.ObservableBuffer

class GridViewModel(model: GridModel) extends ViewModel {

  val records: ObservableBuffer[RecordViewModel] =
    CalculatedBuffer(model.records.map(new RecordViewModel(_)))

}
