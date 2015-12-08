name := "ScalaX Performance project"

version := "1.0"

scalaVersion := "2.11.7"

enablePlugins(JmhPlugin)

// Dependency at compilation-time only (not at runtime).
libraryDependencies += "com.nativelibs4java" %% "scalaxy-streams" % "0.3.4" % "provided"

//mainClass in Compile := Some("")

