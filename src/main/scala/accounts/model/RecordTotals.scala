package accounts.model

import accounts.record.Record

sealed trait RecordTotals {
  def totalDebit: BigDecimal
  def totalCredit: BigDecimal
}

case class ItemTotals(records: Seq[Record]) extends RecordTotals {
  override def totalDebit: BigDecimal = records.map(_.debit).sum
  override def totalCredit: BigDecimal = records.map(_.credit).sum
}

case class AggregationTotals(subTotals: Seq[RecordTotals]) extends RecordTotals {
  override def totalDebit: BigDecimal = subTotals.map(_.totalDebit).sum
  override def totalCredit: BigDecimal = subTotals.map(_.totalCredit).sum
}
