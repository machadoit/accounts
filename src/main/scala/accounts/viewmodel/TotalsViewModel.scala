package accounts.viewmodel

import accounts.core.viewmodel.ViewModel
import accounts.core.viewmodel.ViewModel.CalculatedProperty
import accounts.model.GridModel

class TotalsViewModel(model: GridModel) extends ViewModel {

  val periodCredit = CalculatedProperty(model.periodTotals.totalCredit)
  val periodDebit = CalculatedProperty(model.periodTotals.totalDebit)

  val broughtForwardCredit = CalculatedProperty(model.broughtForwardTotals.totalCredit)
  val broughtForwardDebit = CalculatedProperty(model.broughtForwardTotals.totalDebit)

  val toDateCredit = CalculatedProperty(model.toDateTotals.totalCredit)
  val toDateDebit = CalculatedProperty(model.toDateTotals.totalDebit)

}
