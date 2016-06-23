package accounts.model

import java.time.{LocalDate, Month}

import accounts.record._
import accounts.record.repository.RecordRepository

class GridModel(recordRepository: RecordRepository, filters: FiltersModel) {

  private val all = recordRepository.all

  private[model] def filterAll(predicates: Seq[Option[Record => Boolean]]) = FiltersModel.combine(predicates) match {
    case Some(p) => all.filter(p)
    case None => all
  }

  def records: Seq[Record] = filterAll(filters.allPredicates)
}
