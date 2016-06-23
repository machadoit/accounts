package accounts.model

import accounts.record.Record

class TotalsModel(grid: GridModel, filters: FiltersModel) {

  def periodTotals: RecordTotals = ItemTotals(grid.records)

  private def broughtForwardRecords: Seq[Record] = grid.filterAll(filters.broughtForwardPredicates)
  def broughtForwardTotals: RecordTotals = ItemTotals(broughtForwardRecords)

  def toDateTotals = AggregationTotals(Seq(broughtForwardTotals, periodTotals))
}
