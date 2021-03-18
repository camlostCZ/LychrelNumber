ThisBuild / scalaVersion := "2.13.5"
ThisBuild / version := "1.0"

fork := true
run / javaOptions += "-Xmx4G"
javaOptions += "-XX:+UseStringDeduplication"
javaOptions += "-XX:+UseG1GC"

lazy val root = (project in file("."))
  .settings(
    name := "LychrelNumberApp"
  )
