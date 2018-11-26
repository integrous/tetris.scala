libraryDependencies ++= {
  Seq(
    "org.scala-lang" % "scala-compiler" % "2.11.11",
    "org.scala-lang" % "scala-swing" % "2.11+",
    "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
  )
}
 
organization := "net.tetris"
scalaVersion := "2.11.11"
scalaSource in Compile := baseDirectory.value / "src"
scalaSource in Test := baseDirectory.value / "test"
 
lazy val root = (project in file(".")).
  settings(
    name := "Tetris"
  )