package accounts.model

import accounts.record.repository.{RecordRepository, StandingOrderRepository}

class ShellModel(recordRepo: RecordRepository, soRepo: StandingOrderRepository) {

  val filters = new FiltersModel
  val grid = new GridModel(recordRepo, filters)
  val totals = new TotalsModel(grid, filters)

  val pnl = new PnlModel(grid, filters)

  val addRecord = new AddRecordModel(grid, filters)
  val standingOrders = new StandingOrdersModel(soRepo)
}
