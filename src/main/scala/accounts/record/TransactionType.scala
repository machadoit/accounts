package accounts.record

import TransactionCategory._
import accounts.core.viewmodel.ViewModel
import enumeratum._

/**
  * Pseudo-enumeration of transaction types. Note that Generic and Unknown are defined outside
  * the TransactionType object in order to avoid confusing the enumeratum macro (which can
  * lead to NPEs at runtime).
  */
sealed abstract class TransactionType extends EnumEntry {
  protected def typeValue: Int
  def category: TransactionCategory
  def oneOff: Boolean

  def displayString = s"${category.displayString}: $displaySuffix"
  protected def displaySuffix = ViewModel.displayString(toString)

  def value: Int = typeValue + category.value * 100
}

abstract class VariableTransactionType extends TransactionType {
  override val oneOff: Boolean = false
}

object VariableTransactionType {
  case class Generic(category: TransactionCategory) extends VariableTransactionType {
    override protected val typeValue: Int = 0

    override protected def displaySuffix = getClass.getSimpleName

    override def value: Int = category.value
  }
  case class Unknown(protected val typeValue: Int, category: TransactionCategory)
    extends VariableTransactionType {
    override protected def displaySuffix = (category.value * 100 + value).toString
  }  
}

sealed abstract class FixedTransactionType(
  protected val typeValue: Int,
  val category: TransactionCategory,
  val oneOff: Boolean = false
) extends TransactionType

object TransactionType extends Enum[TransactionType] {

  type Generic = VariableTransactionType.Generic
  def Generic(category: TransactionCategory) = VariableTransactionType.Generic(category)
  type Unknown = VariableTransactionType.Unknown
  def Unknown(typeValue: Int, category: TransactionCategory) = VariableTransactionType.Unknown(typeValue, category)

  case object DailyFood extends FixedTransactionType(11, Food)
  case object Bread extends FixedTransactionType(12, Food)
  case object Fish extends FixedTransactionType(13, Food)
  case object FrozenFood extends FixedTransactionType(14, Food)
  case object OrangeJuiceOrMilk extends FixedTransactionType(15, Food)
  case object Wine extends FixedTransactionType(16, Food)
  case object MineralWater extends FixedTransactionType(17, Food)

  case object JuanFuel extends FixedTransactionType(1, LocalPayment)
  case object Guide extends FixedTransactionType(2, LocalPayment)
  case object Restaurant extends FixedTransactionType(3, LocalPayment)
  case object Laundry extends FixedTransactionType(4, LocalPayment)

  case object StaffWages extends FixedTransactionType(1, Wages)
  case object TempWages extends FixedTransactionType(2, Wages)
  case object SocialSecurity extends FixedTransactionType(3, Wages)

  case object MinibusIncome extends FixedTransactionType(1, Minibus)
  case object MinibusFuel extends FixedTransactionType(2, Minibus)
  case object MinibusMaintenance extends FixedTransactionType(3, Minibus)
  case object MinibusLoanPayment extends FixedTransactionType(4, Minibus)
  case object MinibusInsurance extends FixedTransactionType(5, Minibus)
  case object MinibusLegal extends FixedTransactionType(6, Minibus)

  case object Exodus extends FixedTransactionType(1, Income)
  case object LocalPaymentIncome extends FixedTransactionType(2, Income)
  case object Lunches extends FixedTransactionType(3, Income)
  case object Payphone extends FixedTransactionType(4, Income)
  case object IceCream extends FixedTransactionType(5, Income)
  case object Postcards extends FixedTransactionType(6, Income)
  case object Vat extends FixedTransactionType(7, Income) {
    override protected def displaySuffix: String = "VAT"
  }
  case object Apartments extends FixedTransactionType(8, Income)
  case object Bar extends FixedTransactionType(9, Income)
  case object JerezHorses extends FixedTransactionType(10, Income)
  case object JerezBodega extends FixedTransactionType(11, Income)

  case object CarFuel extends FixedTransactionType(1, Car)
  case object CarMaintenance extends FixedTransactionType(2, Car)
  case object CarTax extends FixedTransactionType(3, Car)

  case object Insurance extends FixedTransactionType(1, Utilities)
  case object Phone extends FixedTransactionType(2, Utilities)
  case object Electricity extends FixedTransactionType(3, Utilities)
  case object Gas extends FixedTransactionType(4, Utilities)
  case object HeatingOil extends FixedTransactionType(5, Utilities)
  case object Logs extends FixedTransactionType(6, Utilities)
  case object Refuse extends FixedTransactionType(7, Utilities)

  case object Sales extends FixedTransactionType(1, Cookbook)
  case object BookCost extends FixedTransactionType(2, Cookbook)
  case object Postage extends FixedTransactionType(3, Cookbook)

  case object HouseGuestRoomOneOff extends FixedTransactionType(1, Maintenance, true)
  case object HouseKitchenOneOff extends FixedTransactionType(2, Maintenance, true)
  case object HouseBathroomOneOff extends FixedTransactionType(3, Maintenance, true)
  case object HouseExteriorOneOff extends FixedTransactionType(4, Maintenance, true)
  case object HousePoolOneOff extends FixedTransactionType(5, Maintenance, true)
  case object GardensOneOff extends FixedTransactionType(6, Maintenance, true)
  case object StudiosOneOff extends FixedTransactionType(7, Maintenance, true)
  case object CortijoBedroomsOneOff extends FixedTransactionType(8, Maintenance, true)
  case object CortijoOfficeOneOff extends FixedTransactionType(9, Maintenance, true)
  case object CortijoPoolOneOff extends FixedTransactionType(10, Maintenance, true)
  case object CortijoKitchenOneOff extends FixedTransactionType(11, Maintenance, true)
  case object CortijoPublicRoomsOneOff extends FixedTransactionType(12, Maintenance, true)
  case object CortijoExteriorOneOff extends FixedTransactionType(13, Maintenance, true)

  case object HouseBedrooms extends FixedTransactionType(51, Maintenance)
  case object HouseKitchen extends FixedTransactionType(52, Maintenance)
  case object HouseBathroom extends FixedTransactionType(53, Maintenance)
  case object HouseExterior extends FixedTransactionType(54, Maintenance)
  case object HousePool extends FixedTransactionType(55, Maintenance)
  case object Gardens extends FixedTransactionType(56, Maintenance)
  case object Studios extends FixedTransactionType(57, Maintenance)
  case object CortijoBedrooms extends FixedTransactionType(58, Maintenance)
  case object CortijoOffice extends FixedTransactionType(59, Maintenance)
  case object CortijoPool extends FixedTransactionType(60, Maintenance)
  case object CortijoKitchen extends FixedTransactionType(61, Maintenance)
  case object CortijoPublicRooms extends FixedTransactionType(62, Maintenance)
  case object CortijoExterior extends FixedTransactionType(63, Maintenance)

  case object Equipment extends FixedTransactionType(1, Miscellaneous)
  case object LegalFees extends FixedTransactionType(2, Miscellaneous)
  case object PoolLoan extends FixedTransactionType(3, Miscellaneous)
  case object Sundries extends FixedTransactionType(4, Miscellaneous)
  case object CleaningMaterials extends FixedTransactionType(5, Miscellaneous)
  case object Advertising extends FixedTransactionType(7, Miscellaneous)
  case object Flamenco extends FixedTransactionType(8, Miscellaneous)

  val values = TransactionCategory.values.map(c => Generic(c)) ++ findValues

  private val transactionValueMap: Map[Int, TransactionType] = values.map {
    tt => tt.value -> tt
  }.toMap

  def withValue(i: Int): TransactionType = transactionValueMap.getOrElse(
    i, Unknown(i % 100, transactionCategory(i / 100))
  )

  private def transactionCategory(value: Int): TransactionCategory =
    TransactionCategory.withValueOpt(value).getOrElse(TransactionCategory.Miscellaneous)

}
