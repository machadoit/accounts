package accounts.model

import java.time.LocalDate

import accounts.record.{Record, Transaction, TransactionCategory, TransactionType}

object Filters {
  def combine(predicates: Seq[Option[Record => Boolean]]): Option[Record => Boolean] =
    predicates.flatten.reduceOption { (p1, p2) => { r => p1(r) && p2(r) } }
}

class Filters {

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

  private def combinedTransactionTypePredicate = transactionTypePredicate orElse transactionCategoryPredicate

  var startDateFilter: Option[LocalDate] = None
  private def startDatePredicate: Option[Record => Boolean] = startDateFilter.map { d =>
    !_.date.isBefore(d)
  }
  private def beforeStartDatePredicate: Option[Record => Boolean] = startDateFilter.map { d =>
    (r: Record) => r.date.isBefore(d) && !r.date.isBefore(d.withDayOfYear(1))
  } orElse Some(_ => false)

  var endDateFilter: Option[LocalDate] = None
  private def endDatePredicate: Option[Record => Boolean] = endDateFilter.map { d =>
    !_.date.isAfter(d)
  }

  private def nonDatePredicates = Seq(combinedTransactionTypePredicate)

  private[model] def allPredicates = nonDatePredicates ++ Seq(startDatePredicate, endDatePredicate)

  private[model] def broughtForwardPredicates = nonDatePredicates :+ beforeStartDatePredicate

}
