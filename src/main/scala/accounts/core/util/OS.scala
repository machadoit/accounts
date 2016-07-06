package accounts.core.util

import enumeratum.{Enum, EnumEntry}

sealed trait OS extends EnumEntry

object OS extends Enum[OS] {
  case object Windows extends OS
  case object MacOS extends OS
  case object Linux extends OS

  override val values: Seq[OS] = findValues
}
