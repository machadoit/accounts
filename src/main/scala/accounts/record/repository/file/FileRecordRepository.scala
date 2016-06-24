package accounts.record.repository.file


import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.{TemporalAccessor, TemporalQuery}

import accounts.record._
import accounts.record.repository.RecordRepository
import com.github.tototoshi.csv._
import com.typesafe.scalalogging.StrictLogging

import scala.collection.mutable
import scala.util.control.NonFatal

class FileRecordRepository(file: java.io.File) extends RecordRepository with StrictLogging {
  private val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy")
  private val localDate = new TemporalQuery[LocalDate] {
    override def queryFrom(temporal: TemporalAccessor): LocalDate = LocalDate.from(temporal)
  }

  override def all: Seq[Record] = stored ++ saved

  private val stored: Seq[Record] = {
    val reader = CSVReader.open(file)
    try {
      for {
        row <- reader.all
      } yield {
        val record = parse(row)
        logger.debug(s"Record: $record")
        record
      }
    } finally {
      reader.close()
    }
  }

  private val saved: mutable.Buffer[Record] = mutable.Buffer()

  override def save(r: Record): Unit = {
    saved += r
  }

  private def parse(fields: Seq[String]): Record = try {
    require(fields.size == 9, s"Expected 9 fields, but got $fields")
    if (fields(2).toInt != 0) {
      Transaction(
        dateFormatter.parse(fields(0), localDate),
        fields(1),
        TransactionType.fromInt(fields(2).toInt),
        BigDecimal(fields(3)),
        BigDecimal(fields(4)),
        IncomeType.withValue(fields(5).toInt),
        fields(6).toInt,
        accountType(fields(7))
      )
    } else {
      OpeningBalance(
        dateFormatter.parse(fields(0), localDate),
        fields(1),
        BigDecimal(fields(3)),
        BigDecimal(fields(4)),
        fields(6).toInt,
        accountType(fields(7))
      )
    }
  } catch {
    case NonFatal(e) => throw new IllegalArgumentException(s"Failed to parse line: $fields", e)
  }

  private def accountType(value: String): AccountType =
    Some(value).filter(!_.isEmpty).flatMap { s =>
      AccountType.valuesToEntriesMap.get(s.toInt)
    }.getOrElse(AccountType.Hotel)

}
