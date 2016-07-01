package accounts.record.repository.file

import java.nio.file.{Files, StandardCopyOption}
import java.time.Month
import java.util.UUID

import accounts.record._
import accounts.record.repository.StandingOrderRepository
import com.github.tototoshi.csv.{CSVReader, CSVWriter}
import com.typesafe.scalalogging.StrictLogging
import FileUtils._
import org.scalactic.TypeCheckedTripleEquals

import scala.util.control.NonFatal

class FileStandingOrderRepository(file: java.io.File)
  extends StandingOrderRepository
  with TypeCheckedTripleEquals
  with StrictLogging {

  override def all: Seq[StandingOrder] = updated.getOrElse(loaded)

  private val loaded: Seq[StandingOrder] = {
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

  private var updated: Option[Seq[StandingOrder]] = None

  override def add(orders: Seq[StandingOrder]): Unit = {
    val writer = CSVWriter.open(file, append = true)
    try {
      orders.foreach { so =>
        val row = format(so)
        logger.debug(s"Storing: $row")
        writer.writeRow(row)
      }
    } finally {
      writer.close()
    }
    updated = Some(all ++ orders)
  }

  override def update(orders: Seq[StandingOrder]): Unit = {
    val updatesById = orders.map(so => so.id -> so).toMap
    val latest = all.map(so => updatesById.getOrElse(so.id, so))
    writeAll(latest)
  }

  override def delete(orders: Seq[StandingOrder]): Unit = {
    val deletedIds = orders.map(_.id)
    val latest = all.filter(so => !deletedIds.contains(so.id))
    writeAll(latest)
  }

  private def writeAll(orders: Seq[StandingOrder]): Unit = {
    val tempFile = java.io.File.createTempFile("sofile", "")
    val writer = CSVWriter.open(tempFile)
    try {
      orders.foreach { so =>
        val row = format(so)
        logger.debug(s"Storing: $row")
        writer.writeRow(row)
      }
    } finally {
      writer.close()
    }
    Files.copy(tempFile.toPath, file.toPath, StandardCopyOption.REPLACE_EXISTING)
    updated = Some(orders)
  }

  private def parse(fields: Seq[String]): StandingOrder = try {
    require(fields.size === 7, s"Expected 7 fields, but got $fields")
    StandingOrder(
      UUID.randomUUID,
      fields(0).toInt,
      fields(1),
      BigDecimal(fields(2)),
      month(fields(3).toInt),
      fields(4).toInt,
      TransactionType.withValue(fields(5).toInt),
      AccountType.withValue(fields(6).toInt)
    )
  } catch {
    case NonFatal(e) => throw new IllegalArgumentException(s"Failed to parse line: $fields", e)
  }

  private def month(i: Int): Option[Month] =
    if (i > 0 && i < 13) Some(Month.of(i))
    else None

  private def format(so: StandingOrder): Seq[Any] = Seq(
    so.dayOfMonth,
    so.description,
    formatBigDecimal(so.debit),
    so.nextMonth.map(_.getValue).getOrElse(0),
    so.monthInterval,
    so.transactionType.value,
    so.accountType.value
  )

}
