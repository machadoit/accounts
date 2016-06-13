package accounts.record

import java.time.LocalDate

sealed trait Record {
  def date: LocalDate
  def description: String
  def debit: BigDecimal
  def credit: BigDecimal
}

case class Transaction(
  date: LocalDate,
  description: String,
  transactionType: TransactionType,
  debit: BigDecimal,
  credit: BigDecimal,
  incomeType: IncomeType,
  reference: Int,
  accountType: AccountType
) extends Record

case class OpeningBalance(
  date: LocalDate,
  description: String,
  debit: BigDecimal,
  credit: BigDecimal
) extends Record
