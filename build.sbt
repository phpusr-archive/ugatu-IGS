import scala.Some

name := "IGS"

val runClass = "igs.coursework.Main"

scalaVersion := "2.10.3"

unmanagedJars in Compile += file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar")

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-swing" % scalaVersion.value,
  "org.scalatest" % "scalatest_2.10" % "2.1.3" % "test",
  "org.pegdown" % "pegdown" % "1.4.2" % "test"
)

// Show durations, short stack traces and generate html report
testOptions in Test += Tests.Argument("-oDS", "-h", "target/report")

javacOptions ++= Seq("-encoding", "UTF-8")



// sbt-assembly (https://github.com/sbt/sbt-assembly)
assemblySettings

// Main-Class for jar
mainClass in Compile := Some(runClass)

// Package depends on tests
packageBin in Compile <<= (packageBin in Compile) dependsOn (test in Test)



// Run task (run-app, runApp)
lazy val runApp = TaskKey[Unit]("run-app", "A custom run task.")

fullRunTask(runApp, Test, runClass)

// Run app depends on tests
runApp <<= runApp dependsOn (test in Test)
