import Dependencies._
import sbt.Keys.libraryDependencies

version      := "0.1"
scalaVersion := "2.13.8"

val oneToOneClassPathDependency = "test->test;compile->compile"

lazy val commonSettings = Seq(
  organization := "fr.fpe",
  scalacOptions ++= Seq(
    "-deprecation:false",
    "-Xfatal-warnings",
    "-language:higherKinds",
    "-Ypatmat-exhaust-depth",
    "off"
  ),
  Compile / doc / scalacOptions += "-no-java-comments",
  Test / classLoaderLayeringStrategy        := ClassLoaderLayeringStrategy.Flat,
  updateOptions                             := updateOptions.value.withGigahorse(false),
  // libraryDependencies += Dependencies.janino % Runtime,
  credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
)

lazy val domain = (project in file("modules/domain"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Dependencies.cats,
    libraryDependencies += scalatest,
  )
  .settings(parallelExecution := false)

lazy val infrastructure = (project in file("modules/infrastructure"))
  .settings(commonSettings)
  .settings(parallelExecution := false)

lazy val bank = (project in file("application/bank"))
  .settings(commonSettings)
  .dependsOn(domain % oneToOneClassPathDependency)
  .dependsOn(infrastructure % oneToOneClassPathDependency)

