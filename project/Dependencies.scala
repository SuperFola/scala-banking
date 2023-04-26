import sbt._

object Dependencies {
  lazy val scalatest = "org.scalatest" %% "scalatest" % "3.2.5"
  lazy val cats = Seq(
    "org.typelevel" %% "cats-core"   % "2.3.0",
    "org.typelevel" %% "cats-effect" % "3.1.1"
  )
}
