import sbt.Keys._
import sbt._

object Dependencies {
  val settings = Seq(
    // repository for scalatestfx
    resolvers += Resolver.bintrayRepo("haraldmaida", "maven")
  )

  val dependencies = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0",
    "ch.qos.logback" % "logback-classic" % "1.1.3",

    "com.beachape" %% "enumeratum" % "1.4.4",
    "com.github.tototoshi" %% "scala-csv" % "1.3.1",
    "org.scalafx" %% "scalafx" % "8.0.92-R10",
    "org.scalactic" %% "scalactic" % "3.0.0-RC4",

    "org.scalatest" %% "scalatest" % "3.0.0-RC4" % Test,
    "io.scalatestfx" %% "scalatestfx" % "0.0.2-alpha" % Test//UiTest.UiTest
  )
}