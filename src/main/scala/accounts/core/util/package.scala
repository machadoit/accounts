package accounts.core

package object util {

  def os: Option[OS] = {
    val osName = sys.props("os.name")
    if (osName.toLowerCase.startsWith("windows")) Some(OS.Windows)
    else if (osName.toLowerCase.startsWith("mac")) Some(OS.MacOS)
    else if (osName.toLowerCase.startsWith("linux")) Some(OS.Linux)
    else None
  }

  implicit class TraversableOps[A](s: Seq[A]) {
    def singleOption: Option[A] = s match {
      case Seq() => None
      case Seq(a) => Some(a)
      case _ => throw new IllegalStateException(s"Expected 0 or 1 elements, but got: $s")
    }
  }

  implicit class TraversableTupleOps[A, +B](s: Seq[(A, B)]) {

    def groupValues: Map[A, Seq[B]] = groupValues(identity)

    def groupValues[C](f: Seq[B] => C): Map[A, C] =
      s.groupBy { case (k, v) => k }
       .mapValues(s => f(s.map { case (k, v) => v }))
  }
}
