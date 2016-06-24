package accounts.viewmodel

import accounts.core.viewmodel.ViewModel
import accounts.core.viewmodel.ViewModel._
import accounts.model.GridModel

import scalafx.collections.ObservableBuffer

class GridViewModel(model: GridModel) extends ViewModel {

  val records: ObservableBuffer[RecordViewModel] =
    CalculatedBuffer(model.records.map(new RecordViewModel(_)))

}
