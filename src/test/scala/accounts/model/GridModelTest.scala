package accounts.model

import accounts.record.AccountType.{Hotel, House}
import accounts.record.IncomeType.{Cash, Cheque, DirectDebit}
import accounts.record.TransactionCategory.Food
import accounts.record.TransactionType.{Generic, IceCream, Phone, Unknown}
import accounts.record.{OpeningBalance, Record, Transaction}
import accounts.record.repository.RecordRepository
import accounts.util.TestUtils._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.{Matchers, WordSpec}

object RecordRepositoryStub extends RecordRepository {
  override def all: Seq[Record] = Seq(
    OpeningBalance(date("2016-02-01"), "opening balance" , debit = 123.45, credit = 0 , 1, Hotel),
    Transaction(date("2016-03-23"), "Cheeseburgers", Unknown(18, Food), debit = 252.75, credit = 0, Cash, 2, Hotel),
    Transaction(date("2016-04-02"), "Vodafone", Phone, debit = 313.10, credit = 0, DirectDebit, 3, Hotel),
    Transaction(date("2016-04-15"), "Cornettos", IceCream, debit = 0, credit = 54.50, Cash, 4, Hotel),
    Transaction(date("2016-05-24"), "Sausages", Generic(Food), debit = 26.42, credit = 0, Cheque, 5, House)
  )

  override def save(r: Record): Unit = {}
}

class GridModelTest extends WordSpec with Matchers with TypeCheckedTripleEquals {

  "A grid model" should {
    val all = RecordRepositoryStub.all

    val filters = new FiltersModel
    println("foo")
    val model = new GridModel(RecordRepositoryStub, filters)

    "contain only hotel records by default" in {
      assert(model.records === all.slice(0, 4))
    }

    "filter on standard transaction types" in {
      filters.transactionTypeFilter = Some(IceCream)
      assert(model.records === Seq(all(3)))
    }

    "filter on unknown transaction types" in {
      filters.transactionTypeFilter = Some(Unknown(18, Food))
      assert(model.records === Seq(all(1)))
    }

//    "filter on transaction categories" in {
//      filters.transactionCategoryFilter = Some(Food)
//      assert(model.records === Seq(all(1), all(4)))
//    }

  }
}
