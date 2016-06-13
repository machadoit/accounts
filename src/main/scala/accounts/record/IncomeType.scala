package accounts.record

import accounts.core.viewmodel.ViewModel
import enumeratum.values.{IntEnum, IntEnumEntry}

sealed abstract class IncomeType(val value: Int) extends IntEnumEntry {
  def displayString: String = ViewModel.displayString(toString)
}


object IncomeType extends IntEnum[IncomeType] {
  case object Cash extends IncomeType(1)
  case object Cheque extends IncomeType(2)
  case object Transfer extends IncomeType(3)
  case object DirectDebit extends IncomeType(4)
  case object Card extends IncomeType(5)
  case object Coop extends IncomeType(6)

  val values = findValues
}
