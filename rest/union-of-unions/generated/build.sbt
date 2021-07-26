name := "example-build"

scalaVersion := "2.13.6"

lazy val root = project
  .in(file("."))
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play-json" % "2.9.2",
      "joda-time" % "joda-time" % "2.10.10",
    )
  )
