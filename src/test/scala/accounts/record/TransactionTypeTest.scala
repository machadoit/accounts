package accounts.record

import accounts.record.TransactionCategory.{Cookbook, Utilities}
import accounts.record.TransactionType._
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.WordSpec

class TransactionTypeTest extends WordSpec with TypeCheckedTripleEquals {

  "Transaction types" should {
    "have correct display strings" in {
      assert(OrangeJuiceOrMilk.displayString === "Food: Orange Juice Or Milk")
      assert(Vat.displayString === "Income: VAT")
      assert(Generic(Cookbook).displayString === "Cookbook: Generic")
      assert(Unknown(25, Utilities).displayString === "Utilities: 725")
    }
  }
}
