package accounts.record

import enumeratum.values.{IntEnum, IntEnumEntry}

sealed abstract class AccountType(val value: Int) extends IntEnumEntry

object AccountType extends IntEnum[AccountType] {
  case object Hotel extends AccountType(1)
  case object House extends AccountType(2)

  val values = findValues
}
