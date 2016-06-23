package accounts.viewmodel

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import accounts.record.{Record, Transaction}

import scalafx.beans.property.ObjectProperty

class RecordViewModel(record: Record) {

  private def transactionField[A](f: Transaction => A): Option[A] = record match {
    case t: Transaction => Some(f(t))
    case _ => None
  }

  val date = ObjectProperty(record.date)
  val description = ObjectProperty(record.description)
  val debit = ObjectProperty(record.debit)
  val credit = ObjectProperty(record.credit)
  val transactionType = ObjectProperty(transactionField(_.transactionType))
  val incomeType = ObjectProperty(transactionField(_.incomeType))
  val reference = ObjectProperty(record.reference)
  val accountType = ObjectProperty(record.accountType)
}

object RecordViewModel {
  private val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
  def formatDate(ld: LocalDate): String = dateFormatter.format(ld)
  def formatDecimal(b: BigDecimal): String = f"$b%.2f"
}