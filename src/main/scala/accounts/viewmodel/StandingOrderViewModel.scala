package accounts.viewmodel

import accounts.record.StandingOrder

import scalafx.beans.property.ObjectProperty

class StandingOrderViewModel(private[viewmodel] val so: StandingOrder) {
  val dayOfMonth = ObjectProperty(so.dayOfMonth)
  val description = ObjectProperty(so.description)
  val debit = ObjectProperty(so.debit)
  val nextMonth = ObjectProperty(so.nextMonth)
  val monthInterval = ObjectProperty(so.monthInterval)
  val transactionType = ObjectProperty(so.transactionType)
  val accountType = ObjectProperty(so.accountType)
}
