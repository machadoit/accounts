package accounts.record

import accounts.core.viewmodel.ViewModel
import enumeratum.values.{IntEnum, IntEnumEntry}

sealed abstract class TransactionCategory(val value: Int) extends IntEnumEntry {
  def displayString: String = ViewModel.displayString(toString)
  def shortString: String = displayString
}

object TransactionCategory extends IntEnum[TransactionCategory] {
  case object BroughtForward extends TransactionCategory(0)
  case object Food extends TransactionCategory(1)
  case object LocalPayment extends TransactionCategory(2) {
    override def shortString = "L/P"
  }
  case object Wages extends TransactionCategory(3)
  case object Minibus extends TransactionCategory(4)
  case object Income extends TransactionCategory(5)
  case object Car extends TransactionCategory(6)
  case object Utilities extends TransactionCategory(7)
  case object Cookbook extends TransactionCategory(8)
  case object Maintenance extends TransactionCategory(9)
  case object Miscellaneous extends TransactionCategory(10) {
    override def shortString = "Misc"
  }

  val values = findValues
}
