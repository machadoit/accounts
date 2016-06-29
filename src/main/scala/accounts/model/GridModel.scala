package accounts.model

import accounts.record._
import accounts.record.repository.RecordRepository

class GridModel(recordRepository: RecordRepository, filters: FiltersModel) {

  var all = recordRepository.all

  private[model] def filterAll(predicates: Seq[Option[Record => Boolean]]) = FiltersModel.combine(predicates) match {
    case Some(p) => all.filter(p)
    case None => all
  }

  def records: Seq[Record] = filterAll(filters.allPredicates)

  private def reload(): Unit = {
    all = recordRepository.all
  }

  def save(r: Record): Unit = {
    recordRepository.save(r)
    reload()
  }

}
