package accounts.model

import accounts.record.{Record, Transaction, TransactionCategory, TransactionType}
import accounts.record.repository.RecordRepository

class GridModel(recordRepository: RecordRepository) {

  private val all = recordRepository.all.sortWith {
    case (t1: Transaction, t2: Transaction) => t1.reference < t2.reference
    case (r1, r2) => r1.date isBefore r2.date
  }

  def records: Seq[Record] = recordPredicate match {
    case Some(p) => all.filter(p)
    case None => all
  }

  var transactionCategoryFilter: Option[TransactionCategory] = None
  private def transactionCategoryPredicate: Option[Record => Boolean] = transactionCategoryFilter.map { tc =>
    r => r match {
      case t: Transaction => t.transactionType.category == tc
      case _ => false
    }
  }

  var transactionTypeFilter: Option[TransactionType] = None
  private def transactionTypePredicate: Option[Record => Boolean] = transactionTypeFilter.map { tt =>
    r => r match {
      case t: Transaction => t.transactionType == tt
      case _ => false
    }
  }

  private def allPredicates = Seq(transactionCategoryPredicate, transactionTypePredicate)
  private def recordPredicate: Option[Record => Boolean] = allPredicates.fold(None) {
    case (Some(p1), Some(p2)) => Some(r => p1(r) && p2(r))
    case (Some(p1), None) => Some(p1)
    case (None, Some(p2)) => Some(p2)
    case (None, None) => None
  }
}
