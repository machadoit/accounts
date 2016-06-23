package accounts.viewmodel

import accounts.model.ShellModel

class ShellViewModel(model: ShellModel) {

  val grid = new GridViewModel(model.grid)
  val filters = new FiltersViewModel(model.filters)
  val totals = new TotalsViewModel(model.totals)

  val pnl = new PnlViewModel(model.pnl)

}
