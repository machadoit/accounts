package accounts.record.repository.file

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.{TemporalAccessor, TemporalQuery}

import accounts.record._
import accounts.record.repository.RecordRepository
import com.github.tototoshi.csv._
import com.typesafe.scalalogging.StrictLogging
import org.scalactic.TypeCheckedTripleEquals

import scala.collection.mutable
import scala.util.control.NonFatal

class FileRecordRepository(file: java.io.File) extends RecordRepository with StrictLogging with TypeCheckedTripleEquals {
  private val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy")
  private val localDate = new TemporalQuery[LocalDate] {
    override def queryFrom(temporal: TemporalAccessor): LocalDate = LocalDate.from(temporal)
  }

  override def all: Seq[Record] = loaded ++ saved

  private val loaded: Seq[Record] = {
    val reader = CSVReader.open(file)
    try {
      for {
        row <- reader.all
      } yield {
        val record = parse(row)
        logger.trace(s"Loaded: $record")
        record
      }
    } finally {
      reader.close()
    }
  }

  private val saved: mutable.Buffer[Record] = mutable.Buffer()

  override def save(r: Record): Unit = {
    val writer = CSVWriter.open(file, append = true)
    val row = format(r)
    logger.debug(s"Storing: $row")
    writer.writeRow(row)
    writer.close()
    saved += r
  }

  private def parse(fields: Seq[String]): Record = try {
    require(fields.size === 9, s"Expected 9 fields, but got $fields")
    if (fields(2).toInt !== 0) {
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

  private def format(r: Record): Seq[Any] = r match {
    case t: Transaction => Seq(
      dateFormatter.format(t.date),
      t.description,
      TransactionType.toInt(t.transactionType),
      format(t.debit),
      format(t.credit),
      t.incomeType.value,
      t.reference,
      t.accountType.value,
      0 // for backward-compatibility
    )
    case b: OpeningBalance => Seq(
      dateFormatter.format(b.date),
      b.description,
      0,
      format(b.debit),
      format(b.credit),
      0,
      1,
      1,
      0 // for backward-compatibility
    )
  }

  private def format(bd: BigDecimal): String = f"$bd%.2f"

  private def accountType(value: String): AccountType =
    Some(value).filter(!_.isEmpty).flatMap { s =>
      AccountType.valuesToEntriesMap.get(s.toInt)
    }.getOrElse(AccountType.Hotel)

}
