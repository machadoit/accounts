package accounts.viewmodel

import java.time.Month

import accounts.core.viewmodel.ViewModel
import accounts.core.viewmodel.ViewModel.{CalculatedBuffer, Property}
import accounts.model.StandingOrdersModel

import scalafx.collections.ObservableBuffer

class StandingOrdersViewModel(model: StandingOrdersModel) extends ViewModel {

  def all: ObservableBuffer[StandingOrderViewModel] =
    CalculatedBuffer(model.all.map(new StandingOrderViewModel(_)))

  val applyMonth = Property[Option[Month]](None)
  val applyYear = Property[Option[Int]](None)

  def applyOrders(): Unit = update {

  }

  def add(orders: Seq[StandingOrderViewModel]): Unit = update {
    model.add(orders.map(_.so))
  }
  def update(orders: Seq[StandingOrderViewModel]): Unit = update {
    model.update(orders.map(_.so))
  }
  def delete(orders: Seq[StandingOrderViewModel]): Unit = update {
    model.delete(orders.map(_.so))
  }

}
