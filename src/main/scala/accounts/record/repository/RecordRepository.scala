package accounts.record.repository

import accounts.record.Record

trait RecordRepository {
  def all: Seq[Record]

}
