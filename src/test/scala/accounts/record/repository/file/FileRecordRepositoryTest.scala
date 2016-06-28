package accounts.record.repository.file

import java.io.File
import java.time.LocalDate

import accounts.record.AccountType.Hotel
import accounts.record.IncomeType._
import accounts.record.{OpeningBalance, Transaction}
import accounts.record.TransactionCategory.Food
import accounts.record.TransactionType.{IceCream, Phone, Unknown}
import org.scalatest.WordSpec

import scala.io.Source

class FileRecordRepositoryTest extends WordSpec {

  private def date(s: String) = LocalDate.parse(s)

  "A file repository" when {
    val fileUrl = getClass.getResource(s"${getClass.getSimpleName}.TRANS")
    val repo = new FileRecordRepository(new File(fileUrl.toURI))

    "records loaded" should {
      val records = repo.all

      "contain the correct number of records" in {
        assert(records.size == 4)
      }

      "contain the expected records" in {
        val expected = Seq(
          OpeningBalance(date("2016-02-01"), "opening balance" , debit = 123.45, credit = 0 , 1, Hotel),
          Transaction(date("2016-03-23"), "Cheeseburgers", Unknown(18, Food), debit = 252.75, credit = 0, Cash, 2, Hotel),
          Transaction(date("2016-04-02"), "Vodafone", Phone, debit = 313.10, credit = 0, DirectDebit, 3, Hotel),
          Transaction(date("2016-04-15"), "Cornettos", IceCream, debit = 0, credit = 54.50, Cash, 4, Hotel)
        )
        assert(records == expected)
      }
    }
  }
}
