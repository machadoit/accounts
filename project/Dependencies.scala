import sbt.Keys._
import sbt._

object Dependencies {

  val dependencyResolvers = Seq(
    // repository for scalatestfx
    Resolver.bintrayRepo("haraldmaida", "maven")
  )

  val dependencies = Seq(
    "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0",
    "ch.qos.logback" % "logback-classic" % "1.1.3",

    "com.beachape" %% "enumeratum" % "1.4.4",
    "com.github.tototoshi" %% "scala-csv" % "1.3.1",
    "org.scalafx" %% "scalafx" % "8.0.92-R10",
    "org.scalactic" %% "scalactic" % "3.0.0-RC4",

    "org.scalatest" %% "scalatest" % "3.0.0-RC4" % Test,
    "io.scalatestfx" %% "scalatestfx" % "0.0.2-alpha" % Test,
    // workaround for NoClassDefFoundError: scoverage/Invoker$ in UI tests
    "org.scoverage" %% "scalac-scoverage-runtime" % "1.1.1" % UiTest.UiTest
  )

  val settings = Seq(
    resolvers ++= dependencyResolvers,
    libraryDependencies ++= dependencies
  )

}