package accounts.core.view

import scalafx.geometry.Pos

case class CellFactory[A](
  text: A => String,
  tooltip: Option[A => Option[String]] = None,
  alignment: Option[Pos] = None
)

object CellFactory {

  def optional[A](
    text: A => String,
    tooltip: Option[A => Option[String]] = None,
    alignment: Option[Pos] = None
  ): CellFactory[Option[A]] =
    CellFactory[Option[A]](
      a => a.map(text).getOrElse(""),
      tooltip.map(f => { a => a.flatMap(f) }),
      alignment
    )

  def optionalWithTooltip[A](text: A => String, tooltip: A => String, alignment: Option[Pos] = None): CellFactory[Option[A]] =
    CellFactory.optional(text, Some(a => Some(tooltip(a))), alignment)
}