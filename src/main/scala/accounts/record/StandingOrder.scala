package accounts.record

import java.time.{LocalDate, Month}
import java.util.UUID

import accounts.record.IncomeType.DirectDebit
import org.scalactic.TypeCheckedTripleEquals

case class StandingOrder(
  id: UUID,
  dayOfMonth: Int,
  description: String,
  debit: BigDecimal,
  nextMonth: Option[Month],
  monthInterval: Int,
  transactionType: TransactionType,
  accountType: AccountType
) extends TypeCheckedTripleEquals {

  def toTransaction(month: Month, year: Int, reference: Int): Option[Transaction] = nextMonth.flatMap { nm =>
    if (month === nm) {
      val date = LocalDate.of(dayOfMonth, nm, year)
      Some(Transaction(date, description, transactionType, debit, 0, DirectDebit, reference, accountType))
    } else {
      None
    }
  }
}
