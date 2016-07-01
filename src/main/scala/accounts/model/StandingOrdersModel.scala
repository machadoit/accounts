package accounts.model

import accounts.record.StandingOrder
import accounts.record.repository.StandingOrderRepository

class StandingOrdersModel(repository: StandingOrderRepository) {
  def all: Seq[StandingOrder] = repository.all

  def add(orders: Seq[StandingOrder]): Unit = repository.add(orders)
  def update(orders: Seq[StandingOrder]): Unit = repository.update(orders)
  def delete(orders: Seq[StandingOrder]): Unit = repository.delete(orders)

}
