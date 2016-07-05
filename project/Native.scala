import com.typesafe.sbt.SbtNativePackager.autoImport._
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.windows.WindowsPlugin
import com.typesafe.sbt.packager.windows.WindowsPlugin.autoImport._
import sbt.Keys._
import sbt._
import sbt.dsl._

object Native {

  lazy val plugins = Seq(JavaAppPackaging, WindowsPlugin)

  lazy val settings = Seq(
    // strip off any version suffixes when building the msi package, since WiX requires
    // version to be of the form x.y.z, where x < 256, y < 256 and z < 65536
    version in Windows := version.value.replaceFirst("-.*", ""),

    // general package information
    maintainer := "Andrew Brett <git@bretts.org>",
    packageSummary := "Accounts",
    packageDescription := "Cortijo Rosario Accounts",

    // wix build information
    wixProductId := "d3b13f53-01b7-4f5c-9c36-44588a83f72c",
    wixProductUpgradeId := "a6f8f3ba-1ecc-421d-9713-4a0ecc5b528d"
  )
}