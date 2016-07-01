package accounts.record.repository.file

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.{TemporalAccessor, TemporalQuery}

import accounts.record.AccountType

object FileUtils {
  private val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
  private val localDate = new TemporalQuery[LocalDate] {
    override def queryFrom(temporal: TemporalAccessor): LocalDate = LocalDate.from(temporal)
  }

  def parseDate(s: String): LocalDate = dateFormatter.parse(s, localDate)
  def formatDate(d: LocalDate): String = dateFormatter.format(d)

  def formatBigDecimal(bd: BigDecimal): String = f"$bd%.2f"

  def parseAccountType(value: String): AccountType =
    Some(value).filter(!_.isEmpty).flatMap { s =>
      AccountType.valuesToEntriesMap.get(s.toInt)
    }.getOrElse(AccountType.Hotel)

}
