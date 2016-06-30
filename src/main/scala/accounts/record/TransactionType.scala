package accounts.record

import TransactionCategory._
import accounts.core.viewmodel.ViewModel
import enumeratum._

sealed abstract class TransactionType(val value: Int, val category: TransactionCategory, val oneOff: Boolean = false) extends EnumEntry {
  def displayString = s"${category.displayString}: $displaySuffix"
  protected def displaySuffix = ViewModel.displayString(toString)
}

object TransactionType extends Enum[TransactionType] {
  case class Generic(override val category: TransactionCategory) extends TransactionType(0, category) {
    override protected def displaySuffix = getClass.getSimpleName
  }
  case class Unknown(override val value: Int, override val category: TransactionCategory)
    extends TransactionType(value, category) {
    override protected def displaySuffix = (category.value * 100 + value).toString
  }

  case object DailyFood extends TransactionType(11, Food)
  case object Bread extends TransactionType(12, Food)
  case object Fish extends TransactionType(13, Food)
  case object FrozenFood extends TransactionType(14, Food)
  case object OrangeJuiceOrMilk extends TransactionType(15, Food)
  case object Wine extends TransactionType(16, Food)
  case object MineralWater extends TransactionType(17, Food)

  case object JuanFuel extends TransactionType(1, LocalPayment)
  case object Guide extends TransactionType(2, LocalPayment)
  case object Restaurant extends TransactionType(3, LocalPayment)
  case object Laundry extends TransactionType(4, LocalPayment)

  case object StaffWages extends TransactionType(1, Wages)
  case object TempWages extends TransactionType(2, Wages)
  case object SocialSecurity extends TransactionType(3, Wages)

  case object MinibusIncome extends TransactionType(1, Minibus)
  case object MinibusFuel extends TransactionType(2, Minibus)
  case object MinibusMaintenance extends TransactionType(3, Minibus)
  case object MinibusLoanPayment extends TransactionType(4, Minibus)
  case object MinibusInsurance extends TransactionType(5, Minibus)
  case object MinibusLegal extends TransactionType(6, Minibus)

  case object Exodus extends TransactionType(1, Income)
  case object LocalPaymentIncome extends TransactionType(2, Income)
  case object Lunches extends TransactionType(3, Income)
  case object Payphone extends TransactionType(4, Income)
  case object IceCream extends TransactionType(5, Income)
  case object Postcards extends TransactionType(6, Income)
  case object Vat extends TransactionType(7, Income) {
    override protected def displaySuffix: String = "VAT"
  }
  case object Apartments extends TransactionType(8, Income)
  case object Bar extends TransactionType(9, Income)
  case object JerezHorses extends TransactionType(10, Income)
  case object JerezBodega extends TransactionType(11, Income)

  case object CarFuel extends TransactionType(1, Car)
  case object CarMaintenance extends TransactionType(2, Car)
  case object CarTax extends TransactionType(3, Car)

  case object Insurance extends TransactionType(1, Utilities)
  case object Phone extends TransactionType(2, Utilities)
  case object Electricity extends TransactionType(3, Utilities)
  case object Gas extends TransactionType(4, Utilities)
  case object HeatingOil extends TransactionType(5, Utilities)
  case object Logs extends TransactionType(6, Utilities)
  case object Refuse extends TransactionType(7, Utilities)

  case object Sales extends TransactionType(1, Cookbook)
  case object BookCost extends TransactionType(2, Cookbook)
  case object Postage extends TransactionType(3, Cookbook)

  case object HouseGuestRoomOneOff extends TransactionType(1, Maintenance, true)
  case object HouseKitchenOneOff extends TransactionType(2, Maintenance, true)
  case object HouseBathroomOneOff extends TransactionType(3, Maintenance, true)
  case object HouseExteriorOneOff extends TransactionType(4, Maintenance, true)
  case object HousePoolOneOff extends TransactionType(5, Maintenance, true)
  case object GardensOneOff extends TransactionType(6, Maintenance, true)
  case object StudiosOneOff extends TransactionType(7, Maintenance, true)
  case object CortijoBedroomsOneOff extends TransactionType(8, Maintenance, true)
  case object CortijoOfficeOneOff extends TransactionType(9, Maintenance, true)
  case object CortijoPoolOneOff extends TransactionType(10, Maintenance, true)
  case object CortijoKitchenOneOff extends TransactionType(11, Maintenance, true)
  case object CortijoPublicRoomsOneOff extends TransactionType(12, Maintenance, true)
  case object CortijoExteriorOneOff extends TransactionType(13, Maintenance, true)

  case object HouseBedrooms extends TransactionType(51, Maintenance)
  case object HouseKitchen extends TransactionType(52, Maintenance)
  case object HouseBathroom extends TransactionType(53, Maintenance)
  case object HouseExterior extends TransactionType(54, Maintenance)
  case object HousePool extends TransactionType(55, Maintenance)
  case object Gardens extends TransactionType(56, Maintenance)
  case object Studios extends TransactionType(57, Maintenance)
  case object CortijoBedrooms extends TransactionType(58, Maintenance)
  case object CortijoOffice extends TransactionType(59, Maintenance)
  case object CortijoPool extends TransactionType(60, Maintenance)
  case object CortijoKitchen extends TransactionType(61, Maintenance)
  case object CortijoPublicRooms extends TransactionType(62, Maintenance)
  case object CortijoExterior extends TransactionType(63, Maintenance)

  case object Equipment extends TransactionType(1, Miscellaneous)
  case object LegalFees extends TransactionType(2, Miscellaneous)
  case object PoolLoan extends TransactionType(3, Miscellaneous)
  case object Sundries extends TransactionType(4, Miscellaneous)
  case object CleaningMaterials extends TransactionType(5, Miscellaneous)
  case object Advertising extends TransactionType(7, Miscellaneous)
  case object Flamenco extends TransactionType(8, Miscellaneous)

  val values = TransactionCategory.values.map(c => Generic(c)) ++ findValues

  private val transactionValueMap = values.map(tt => toInt(tt) -> tt).toMap

  def fromInt(value: Int): TransactionType = transactionValueMap.getOrElse(value, {
    if (value < 100) Generic(transactionCategory(value))
    else Unknown(value % 100, transactionCategory(value / 100))
  })

  def toInt(tt: TransactionType): Int = tt match {
    case Generic(category) => category.value
    case _ => tt.value + tt.category.value * 100
  }

  private def transactionCategory(value: Int): TransactionCategory =
    TransactionCategory.valuesToEntriesMap.getOrElse(value, TransactionCategory.Miscellaneous)


}
