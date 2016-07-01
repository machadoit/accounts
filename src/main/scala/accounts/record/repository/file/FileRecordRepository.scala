package accounts.record.repository.file

import accounts.record._
import accounts.record.repository.RecordRepository
import com.github.tototoshi.csv._
import com.typesafe.scalalogging.StrictLogging
import org.scalactic.TypeCheckedTripleEquals
import FileUtils._

import scala.collection.mutable
import scala.util.control.NonFatal

class FileRecordRepository(file: java.io.File) extends RecordRepository with StrictLogging with TypeCheckedTripleEquals {

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
    try {
      val row = format(r)
      logger.debug(s"Storing: $row")
      writer.writeRow(row)
    } finally {
      writer.close()
    }
    saved += r
  }

  private def parse(fields: Seq[String]): Record = try {
    require(fields.size === 9, s"Expected 9 fields, but got $fields")
    if (fields(2).toInt !== 0) {
      Transaction(
        parseDate(fields(0)),
        fields(1),
        TransactionType.withValue(fields(2).toInt),
        BigDecimal(fields(3)),
        BigDecimal(fields(4)),
        IncomeType.withValue(fields(5).toInt),
        fields(6).toInt,
        parseAccountType(fields(7))
      )
    } else {
      OpeningBalance(
        parseDate(fields(0)),
        fields(1),
        BigDecimal(fields(3)),
        BigDecimal(fields(4)),
        fields(6).toInt,
        parseAccountType(fields(7))
      )
    }
  } catch {
    case NonFatal(e) => throw new IllegalArgumentException(s"Failed to parse line: $fields", e)
  }

  private def format(r: Record): Seq[Any] = r match {
    case t: Transaction => Seq(
      formatDate(t.date),
      t.description,
      t.transactionType.value,
      formatBigDecimal(t.debit),
      formatBigDecimal(t.credit),
      t.incomeType.value,
      t.reference,
      t.accountType.value,
      0 // for backward-compatibility
    )
    case b: OpeningBalance => Seq(
      formatDate(b.date),
      b.description,
      0,
      formatBigDecimal(b.debit),
      formatBigDecimal(b.credit),
      0,
      1,
      1,
      0 // for backward-compatibility
    )
  }

}
