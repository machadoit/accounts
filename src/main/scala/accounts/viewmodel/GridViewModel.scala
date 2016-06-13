package accounts.viewmodel

import accounts.core.viewmodel.ViewModel
import accounts.core.viewmodel.ViewModel._
import accounts.model.GridModel
import accounts.record.{TransactionCategory, TransactionType}

import scalafx.collections.ObservableBuffer

class GridViewModel(model: GridModel) extends ViewModel {

  val records: ObservableBuffer[RecordViewModel] =
    CalculatedBuffer(model.records.map(new RecordViewModel(_)))

  val textFilter = Property[Option[Int]](None)
  textFilter.onUiChange {
    o => o match {
      case Some(i) if i < 100 =>
        transactionCategoryFilter() = Some(TransactionCategory.withValue(i))
        transactionTypeFilter() = None
      case Some(i) =>
        transactionCategoryFilter() = None
        transactionTypeFilter() = Some(TransactionType.fromInt(i))
      case None =>
        transactionCategoryFilter() = None
        transactionTypeFilter() = None
    }
  }

  val transactionCategoryFilters = ObservableBuffer(
    None +: TransactionCategory.values.sortBy(_.value).map(Some(_))
  )
  val transactionCategoryFilter = Binding[Option[TransactionCategory]](model.transactionCategoryFilter) {
    model.transactionCategoryFilter = _
  }
  transactionCategoryFilter.onUiChange {
    textFilter() = transactionCategoryFilter().map(_.value)
    transactionTypeFilter() = None
  }

  val transactionTypeFilters = ObservableBuffer(
    None +: TransactionType.values.sortBy(TransactionType.toInt).map(Some(_))
  )
  val transactionTypeFilter = Binding[Option[TransactionType]](model.transactionTypeFilter) {
    model.transactionTypeFilter = _
  }
  transactionTypeFilter.onUiChange {
    textFilter() = transactionTypeFilter().map(TransactionType.toInt(_))
    transactionCategoryFilter() = None
  }

}
