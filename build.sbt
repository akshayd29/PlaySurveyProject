name := """SurveyProject"""

version := "1.0-SNAPSHOT"

lazy val root = project.in(file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  cache, ws, jdbc, javaEbean,
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "com.typesafe.play" %% "play-slick" % "0.8.1",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.specs2" %% "specs2-core" % "3.6.5" % "test",
  "org.mockito" %   "mockito-all"   % "1.9.5",
  "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
)
