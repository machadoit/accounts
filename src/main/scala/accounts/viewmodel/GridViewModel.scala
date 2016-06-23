package accounts.viewmodel

import java.time.{LocalDate, Month}

import accounts.core.viewmodel.ViewModel
import accounts.core.viewmodel.ViewModel._
import accounts.model.GridModel
import accounts.record.{AccountType, TransactionCategory, TransactionType}

import scalafx.collections.ObservableBuffer

class GridViewModel(model: GridModel) extends ViewModel {

  val totals = new TotalsViewModel(model)

  val records: ObservableBuffer[RecordViewModel] =
    CalculatedBuffer(model.records.map(new RecordViewModel(_)))

  val accountTypeFilters = ObservableBuffer(
    None +: AccountType.values.map(Some(_))
  )
  val accountTypeFilter = Binding[Option[AccountType]](model.filters.accountTypeFilter) {
    model.filters.accountTypeFilter = _
  }

  val textFilter = Property[Option[Int]](None)
  textFilter.onUiChange { o =>
    o match {
      case Some(i) if i < 100 =>
        transactionCategoryFilter() = Some(TransactionCategory.withValue(i))
        transactionTypeFilter() = None
      case Some(i) =>
        val tt = TransactionType.fromInt(i)
        transactionCategoryFilter() = Some(tt.category)
        transactionTypeFilter() = Some(tt)
      case None =>
        transactionCategoryFilter() = None
        transactionTypeFilter() = None
    }
  }

  val transactionCategoryFilters = ObservableBuffer(
    None +: TransactionCategory.values.sortBy(_.value).map(Some(_))
  )
  val transactionCategoryFilter = Binding[Option[TransactionCategory]](model.filters.transactionCategoryFilter) {
    model.filters.transactionCategoryFilter = _
  }
  transactionCategoryFilter.onUiChange { tc =>
    textFilter() = tc.map(_.value)
    transactionTypeFilter() = None
  }

  val transactionTypeFilters = ObservableBuffer(
    None +: TransactionType.values.sortBy(TransactionType.toInt).map(Some(_))
  )
  val transactionTypeFilter = Binding[Option[TransactionType]](model.filters.transactionTypeFilter) {
    model.filters.transactionTypeFilter = _
  }
  transactionTypeFilter.onUiChange { tt =>
    // TODO: Don't reset category when type is set back to None
    textFilter() = tt.map(TransactionType.toInt(_))
    transactionCategoryFilter() = tt.map(_.category)
  }

  val startDateFilter = Binding[Option[LocalDate]](model.filters.startDateFilter) {
    model.filters.startDateFilter = _
  }
  startDateFilter.onUiChange { d =>
    if (d.isDefined) {
      monthFilter() = None
      yearFilter() = None
    }
  }

  val endDateFilter = Binding[Option[LocalDate]](model.filters.endDateFilter) {
    model.filters.endDateFilter = _
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
