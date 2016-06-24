package accounts.viewmodel

import java.time.{LocalDate, Month}

import accounts.core.viewmodel.ViewModel
import accounts.core.viewmodel.ViewModel.CalculatedBuffer
import accounts.model.{PnlModel, PnlPeriodSummary, PnlSummary, PnlTotal}
import accounts.record.TransactionCategory

import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer

object PnlSummaryName {
  def monthStr(m: Month) = m.toString.take(3).toLowerCase.capitalize
}

sealed trait PnlSummaryName
case class MonthSummaryName(month: Month, year: Int) extends PnlSummaryName {
  override def toString = s"${PnlSummaryName.monthStr(month)} $year"
}
case class PartialMonthSummaryName(startDate: LocalDate, endDayOfMonth: Int) extends PnlSummaryName {
  val monthStr = PnlSummaryName.monthStr(startDate.getMonth)
  override def toString =
    s"$monthStr ${startDate.getDayOfMonth}-${endDayOfMonth} ${startDate.getYear}"
}
case object TotalsName extends PnlSummaryName {
  override def toString = "Total"
}

sealed trait PnlSummaryViewModel {
  protected def model: PnlSummary

  def name: ObjectProperty[PnlSummaryName]

  private val breakdowns = model.breakdowns.mapValues(ObjectProperty(_))

  def profit(tc: TransactionCategory): ObjectProperty[BigDecimal] = breakdowns.getOrElse(tc, ObjectProperty(BigDecimal(0)))

  def totalProfit: ObjectProperty[BigDecimal] = ObjectProperty(model.totalProfit)

  def isTotal: Boolean
}

class PnlPeriodSummaryViewModel(val model: PnlPeriodSummary) extends PnlSummaryViewModel {
  val name = ObjectProperty[PnlSummaryName] {
    if (model.completeMonth) MonthSummaryName(model.startDate.getMonth, model.startDate.getYear)
    else PartialMonthSummaryName(model.startDate, model.endDayOfMonth)
  }

  val isTotal = false
}

class PnlTotalsViewModel(val model: PnlTotal) extends PnlSummaryViewModel {
  val name = ObjectProperty[PnlSummaryName](TotalsName)

  val isTotal = true
}



object PnlViewModel {
  def formatDecimal(b: BigDecimal): String = f"$b%.0f"
}

class PnlViewModel(model: PnlModel) extends ViewModel {

  def categories = TransactionCategory.values diff Seq(TransactionCategory.BroughtForward)

  def all: ObservableBuffer[PnlSummaryViewModel] =
    CalculatedBuffer(model.summaries.map(new PnlPeriodSummaryViewModel(_)) :+ new PnlTotalsViewModel(model.total))

}
