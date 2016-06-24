package accounts.viewmodel

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.{TemporalAccessor, TemporalQuery}

import accounts.record.{Record, Transaction}

import scalafx.beans.property.ObjectProperty
import scalafx.util.StringConverter

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
  def toDate(s: String): LocalDate = dateFormatter.parse(s, new TemporalQuery[LocalDate] {
    override def queryFrom(temporal: TemporalAccessor): LocalDate = LocalDate.from(temporal)
  })

  val dateConverter: StringConverter[LocalDate] = StringConverter[LocalDate](
    s => if (s != null && !s.isEmpty) RecordViewModel.toDate(s) else null,
    d => if (d != null) RecordViewModel.formatDate(d) else ""
  )

  val optionIntConverter = StringConverter[Option[Int]](
    Option(_).filter(!_.isEmpty).map(_.toInt),
    _.map(_.toString).getOrElse("")
  )

  val optionPositiveBigDecimalConverter = StringConverter[Option[BigDecimal]](
    Option(_).filter(!_.isEmpty).map(BigDecimal(_)).filter(_ > 0),
    _.map(formatDecimal).getOrElse("")
  )

  val optionStringConverter = StringConverter[Option[String]](
    Option(_).filter(!_.isEmpty),
    _.getOrElse("")
  )

  def formatDecimal(b: BigDecimal): String = f"$b%.2f"
}