name := "Accounts"

organization := "org.bretts"

version := "0.99.0"

scalaVersion := "2.11.8"

scalacOptions := Seq(
  "-target:jvm-1.8",
  "-Xfuture", "-Xexperimental",
  "-Xcheckinit",
  "-Xfatal-warnings",
  "-deprecation", "-unchecked", "-feature",
  "-Xlint:_",
  "-Ywarn-adapted-args", "-Ywarn-dead-code", "-Ywarn-inaccessible", "-Ywarn-infer-any",
  "-Ywarn-nullary-override", "-Ywarn-nullary-unit", "-Ywarn-unused", "-Ywarn-unused-import"
  // "-Ywarn-numeric-widen", "-Ywarn-value-discard" <-- overly stringent warnings
)

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3"

libraryDependencies += "com.beachape" %% "enumeratum" % "1.4.4"
libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.1"
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.92-R10"
libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % Test

mainClass in (Compile, run) := Some("accounts.app.Accounts")
// Fix for 'WARNING: Resource "com/sun/javafx/scene/control/skin/modena/modena.css" not found' when
// running via 'sbt run'
unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/lib/ext/jfxrt.jar"))
