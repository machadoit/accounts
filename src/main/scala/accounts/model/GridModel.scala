package accounts.model

import java.time.{LocalDate, Month}

import accounts.record._
import accounts.record.repository.RecordRepository

class GridModel(recordRepository: RecordRepository) {

  val filters = new Filters

  private val all = recordRepository.all

  private def filterAll(predicates: Seq[Option[Record => Boolean]]) = Filters.combine(predicates) match {
    case Some(p) => all.filter(p)
    case None => all
  }

  def records: Seq[Record] = filterAll(filters.allPredicates)
  def periodTotals: RecordTotals = ItemTotals(records)

  private def broughtForwardRecords: Seq[Record] = filterAll(filters.broughtForwardPredicates)
  def broughtForwardTotals: RecordTotals = ItemTotals(broughtForwardRecords)

  def toDateTotals = AggregationTotals(Seq(broughtForwardTotals, periodTotals))
}
