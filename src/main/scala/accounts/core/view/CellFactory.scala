package accounts.core.view

case class CellFactory[A] private (text: A => String, tooltip: A => Option[String])

object CellFactory {

  def apply[A](text: A => String): CellFactory[A] =
    CellFactory(text, _ => None)

  def optional[A](text: A => String): CellFactory[Option[A]] =
    CellFactory(_.map(text).getOrElse(""), _ => None)

  def optionalWithTooltip[A](text: A => String, tooltip: A => String): CellFactory[Option[A]] =
    CellFactory(_.map(text).getOrElse(""), _.map(tooltip))
}