name := "Accounts"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3"

libraryDependencies += "com.beachape" %% "enumeratum" % "1.4.4"
libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.1"
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.92-R10"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % Test

mainClass in (Compile, run) := Some("accounts.app.Accounts")
// Fix for "WARNING: Resource "com/sun/javafx/scene/control/skin/modena/modena.css" not found"
unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/lib/ext/jfxrt.jar"))

enablePlugins(JavaAppPackaging)
enablePlugins(WindowsPlugin)

// general package information
maintainer := "Andrew Brett <git@bretts.org>"
packageSummary := "accounts"
packageDescription := "Cortijo Rosario Accounts"

// wix build information
wixProductId := "d3b13f53-01b7-4f5c-9c36-44588a83f72c"
wixProductUpgradeId := "a6f8f3ba-1ecc-421d-9713-4a0ecc5b528d"
