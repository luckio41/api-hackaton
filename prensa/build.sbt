name := """prensa"""
organization := "cl.biobiochile"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"
val reactiveMongoVer = "0.11.14"

libraryDependencies ++= Seq(
  filters,
  jdbc,
  "com.typesafe.play" %% "anorm" % "2.5.1",
  "org.postgresql" % "postgresql" % "9.4.1212",
  "org.reactivemongo" %% "play2-reactivemongo" % reactiveMongoVer,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % "test"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "cl.biobiochile.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "cl.biobiochile.binders._"