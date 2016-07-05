import com.typesafe.sbt.GitPlugin.autoImport._
import com.typesafe.sbt.GitVersioning
import sbt._

object Git {

  lazy val plugins = Seq(GitVersioning)

  lazy val settings = Seq(
    git.baseVersion := "1.0"
  )
}