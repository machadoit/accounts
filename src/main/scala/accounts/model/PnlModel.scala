package accounts.model

import java.time.{LocalDate, Month}

import accounts.record.{Record, Transaction, TransactionCategory}
import accounts.core.util._

sealed trait PnlSummary {
  def breakdowns: Map[TransactionCategory, BigDecimal]

  def totalProfit: BigDecimal = breakdowns.values.sum
}

case class PnlPeriodSummary(
  startDate: LocalDate,
  endDayOfMonth: Int,
  breakdowns: Map[TransactionCategory, BigDecimal]
) extends PnlSummary {
  def completeMonth: Boolean = startDate.getDayOfMonth == 1 && endDayOfMonth == startDate.lengthOfMonth

  override def toString: String = {
    val periodStr = {
      if (completeMonth) s"${startDate.getMonth.toString.toLowerCase.capitalize} ${startDate.getYear}"
      else s"${startDate.getMonth.toString.toLowerCase.capitalize} ${startDate.getDayOfMonth}-${endDayOfMonth} ${startDate.getYear}"
    }

    def breakdownStr(breakdown: (TransactionCategory, BigDecimal)) = {
      val (tc, pnl) = breakdown
      f"${tc.displayString}: $pnl%.0f"
    }

    s"$periodStr [${breakdowns.toSeq.sortBy { case (tc, _) => tc.value }.map(breakdownStr).mkString(", ")}]"
  }
}

case class PnlTotal(breakdowns: Map[TransactionCategory, BigDecimal]) extends PnlSummary

class PnlModel(grid: GridModel, filtersModel: FiltersModel) {

  def summaries: Seq[PnlPeriodSummary] = {
    val byMonthYear: Map[(Month, Int), Seq[Record]] = grid.records.groupBy { r => (r.date.getMonth, r.date.getYear) }
    byMonthYear.map { case ((month, year), records) =>
      val transactions = records.collect { case t: Transaction => t }
      val byCategory = transactions.groupBy(_.transactionType.category)
      val breakdowns = byCategory.mapValues { records =>
        records.map(_.credit).sum - records.map(_.debit).sum
      }

      val monthStart = LocalDate.of(year, month, 1)
      val periodStart = filtersModel.startDateFilter match {
        case Some(sd) => if (monthStart.isAfter(sd)) monthStart else sd
        case None => monthStart
      }

      val monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth)
      val periodEnd = filtersModel.endDateFilter match {
        case Some(ed) => if (monthEnd.isBefore(ed)) monthEnd else ed
        case None => monthEnd
      }

      PnlPeriodSummary(periodStart, periodEnd.getDayOfMonth, breakdowns)
    }.toSeq.sortWith((a, b) => a.startDate.isBefore(b.startDate))
  }

  def total: PnlTotal = PnlTotal(summaries.flatMap(_.breakdowns).groupValues(_.sum))

}
