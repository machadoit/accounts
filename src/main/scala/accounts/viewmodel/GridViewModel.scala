package accounts.viewmodel

import java.time.{LocalDate, Month}

import accounts.core.viewmodel.ViewModel
import accounts.core.viewmodel.ViewModel._
import accounts.model.GridModel
import accounts.record.{TransactionCategory, TransactionType}

import scalafx.collections.ObservableBuffer

class GridViewModel(model: GridModel) extends ViewModel {

  val records: ObservableBuffer[RecordViewModel] =
    CalculatedBuffer(model.records.map(new RecordViewModel(_)))

  val textFilter = Property[Option[Int]](None)
  textFilter.onUiChange { o =>
    o match {
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

  val startDateFilter = Binding[Option[LocalDate]](model.startDateFilter) {
    model.startDateFilter = _
  }
  startDateFilter.onUiChange { d =>
    if (d.isDefined) {
      monthFilter() = None
      yearFilter() = None
    }
  }

  val endDateFilter = Binding[Option[LocalDate]](model.endDateFilter) {
    model.endDateFilter = _
  }
  endDateFilter.onUiChange { d =>
    if (d.isDefined) {
      monthFilter() = None
      yearFilter() = None
    }
  }

  val monthFilters = ObservableBuffer(
    None +: Month.values.map(Some(_)).toSeq
  )
  val monthFilter = Property[Option[Month]](None)
  monthFilter.onUiChange { m =>
    updateStartEndFilters(m, yearFilter())
  }

  val yearFilter = Property[Option[Int]](None)
  yearFilter.onUiChange { y =>
    updateStartEndFilters(monthFilter(), y)
  }

  private def updateStartEndFilters(month: Option[Month], year: Option[Int]): Unit = (month, year) match {
    case (Some(m), Some(y)) =>
      val monthStart = LocalDate.of(y, m, 1)
      startDateFilter() = Some(monthStart)
      endDateFilter() = Some(monthStart.withDayOfMonth(monthStart.lengthOfMonth))
    case (None, Some(y)) =>
      startDateFilter() = Some(LocalDate.of(y, 1, 1))
      endDateFilter() = Some(LocalDate.of(y, 12, 31))
    case _ =>
      startDateFilter() = None
      endDateFilter() = None
  }

}
