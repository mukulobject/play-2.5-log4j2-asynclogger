name := """play2.5-log4j2-asynclogger"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava).disablePlugins(PlayLogback)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.5",
  "org.apache.logging.log4j" % "log4j-core" % "2.5",
  "org.apache.logging.log4j" % "log4j-api" % "2.5"
)
