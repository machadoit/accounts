name := "Accounts"

organization := "org.bretts"

enablePlugins(GitVersioning)
git.baseVersion := "1.0"

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

// repository for scalatestfx
resolvers += Resolver.bintrayRepo("haraldmaida", "maven")

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.4.0"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3"

libraryDependencies += "com.beachape" %% "enumeratum" % "1.4.4"
libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.1"
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.92-R10"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.0-RC4"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0-RC4" % Test
libraryDependencies += "io.scalatestfx" %% "scalatestfx" % "0.0.2-alpha" % Test

mainClass in (Compile, run) := Some("accounts.app.Accounts")
// Fix for 'WARNING: Resource "com/sun/javafx/scene/control/skin/modena/modena.css" not found' when
// running via 'sbt run'
unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/lib/ext/jfxrt.jar"))
unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))

// Ensure UI tests run in headless mode
fork in Test := true
val defaultExtDirs = sys.props("java.ext.dirs")
javaOptions in Test += "-Djava.ext.dirs=" + (baseDirectory.value / "lib" / "ext") + java.io.File.pathSeparator + defaultExtDirs
javaOptions in Test += "-Dtestfx.robot=glass"
javaOptions in Test += "-Dglass.platform=Monocle"
javaOptions in Test += "-Dmonocle.platform=Headless"
javaOptions in Test += "-Dprism.order=sw"

enablePlugins(JavaAppPackaging, WindowsPlugin)

// strip off any version suffixes when building the msi package, since WiX requires
// version to be of the form x.y.z, where x < 256, y < 256 and z < 65536
version in Windows := version.value.replaceFirst("-.*", "")

// general package information
maintainer := "Andrew Brett <git@bretts.org>"
packageSummary := "Accounts"
packageDescription := "Cortijo Rosario Accounts"

// wix build information
wixProductId := "d3b13f53-01b7-4f5c-9c36-44588a83f72c"
wixProductUpgradeId := "a6f8f3ba-1ecc-421d-9713-4a0ecc5b528d"
