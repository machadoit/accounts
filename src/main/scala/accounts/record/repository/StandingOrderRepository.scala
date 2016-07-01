package accounts.record.repository

import accounts.record.StandingOrder

trait StandingOrderRepository {
  def all: Seq[StandingOrder]

  def add(orders: Seq[StandingOrder]): Unit
  def update(orders: Seq[StandingOrder]): Unit
  def delete(orders: Seq[StandingOrder]): Unit
}
