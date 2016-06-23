package accounts.viewmodel

import java.time.Month

import accounts.core.viewmodel.ViewModel
import accounts.core.viewmodel.ViewModel.CalculatedBuffer
import accounts.model.{PnlModel, PnlPeriodSummary, PnlSummary, PnlTotal}
import accounts.record.TransactionCategory

import scalafx.beans.property.ObjectProperty
import scalafx.collections.ObservableBuffer

sealed trait PnlSummaryViewModel {
  protected def model: PnlSummary

  def name: ObjectProperty[String]

  private val breakdowns = model.breakdowns.mapValues(ObjectProperty(_))

  def profit(tc: TransactionCategory): ObjectProperty[BigDecimal] = breakdowns.getOrElse(tc, ObjectProperty(BigDecimal(0)))

  def totalProfit: ObjectProperty[BigDecimal] = ObjectProperty(model.totalProfit)
}

class PnlPeriodSummaryViewModel(val model: PnlPeriodSummary) extends PnlSummaryViewModel {

  private def monthStr(m: Month) = model.startDate.getMonth.toString.take(3).toLowerCase.capitalize

  val name = ObjectProperty {
    val month = monthStr(model.startDate.getMonth)
    if (model.completeMonth) s"$month ${model.startDate.getYear}"
    else s"$month ${model.startDate.getDayOfMonth}-${model.endDayOfMonth} ${model.startDate.getYear}"
  }

  val isTotal = false
}

class PnlTotalsViewModel(val model: PnlTotal) extends PnlSummaryViewModel {

  val name = ObjectProperty("Total")

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
