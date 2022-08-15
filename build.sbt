ThisBuild / version       := "0.1.0"
ThisBuild / versionScheme := Some("early-semver")
ThisBuild / organization  := "io.github.scottweaver"
ThisBuild / description   := "Idiomatic build environment for ZIO ecosystem projects."
ThisBuild / homepage      := Some(url("https://github.com/scottweaver/zio-sbt-project-plugin"))
ThisBuild / startYear     := Some(2021)
ThisBuild / licenses      := List("Apache-2.0" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/scottweaver/zio-sbt-project-plugin"),
    "scm:git@github.com:scottweaver/zio-sbt-project-plugin.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id = "scottweaver",
    name = "Scott T Weaver",
    email = "scott.t.weaver@gmail.com",
    url = url("https://scottweaver.github.io/")
  )
)

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    name := "zio-sbt-project-plugin",
    libraryDependencies ++= Seq(
      "org.snakeyaml" % "snakeyaml-engine" % "2.3"
    ),
    addSbtPlugin("com.github.sbt"                    % "sbt-pgp"                   % "2.1.2"),
    addSbtPlugin("ch.epfl.scala"                     % "sbt-bloop"                 % "1.4.9"),
    addSbtPlugin("ch.epfl.scala"                     % "sbt-scalafix"              % "0.10.1"),
    addSbtPlugin("com.eed3si9n"                      % "sbt-buildinfo"             % "0.10.0"),
    addSbtPlugin("com.eed3si9n"                      % "sbt-unidoc"                % "0.4.3"),
    addSbtPlugin("com.github.cb372"                  % "sbt-explicit-dependencies" % "0.2.16"),
    addSbtPlugin("com.thoughtworks.sbt-api-mappings" % "sbt-api-mappings"          % "3.0.0"),
    addSbtPlugin("com.typesafe"                      % "sbt-mima-plugin"           % "0.9.0"),
    addSbtPlugin("de.heikoseeberger"                 % "sbt-header"                % "5.6.0"),
    addSbtPlugin("org.scalameta"                     % "sbt-mdoc"                  % "2.3.3"),
    addSbtPlugin("org.scalameta"                     % "sbt-scalafmt"              % "2.4.3")
  )
