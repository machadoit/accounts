publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

credentials += {
  val travisCredentials = for {
    username <- Option(System.getenv().get("SONATYPE_USERNAME"))
    password <- Option(System.getenv().get("SONATYPE_PASSWORD"))
  } yield {
    Credentials(
      "Sonatype Nexus Repository Manager",
      "oss.sonatype.org",
      username,
      password)
  }

  travisCredentials.getOrElse(Credentials(Path.userHome / ".ivy2" / ".credentials"))
}

publishMavenStyle := true

//pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/bretts-org/accounts</url>
    <licenses>
      <license>
        <name>Apache 2</name>
        <url>https://www.apache.org/licenses/LICENSE-2.0.txt</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:bretts-org/accounts.git</url>
      <connection>scm:git:git@github.com:bretts-org/accounts.git</connection>
    </scm>
    <developers>
      <developer>
        <id>aebrett</id>
        <name>Andrew Brett</name>
        <url>http://www.bretts.org</url>
      </developer>
    </developers>)