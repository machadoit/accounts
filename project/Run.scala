import sbt._
import sbt.Keys._

object Run {
  lazy val settings = Seq(
    mainClass in (Compile, run) := Some("accounts.app.Accounts"),
    // Fix for 'WARNING: Resource "com/sun/javafx/scene/control/skin/modena/modena.css" not found' when
    // running via 'sbt run'
    unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/lib/ext/jfxrt.jar")),
    unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))
  )
}
