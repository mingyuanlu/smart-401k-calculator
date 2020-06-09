ThisBuild/version      := "0.1.0"
ThisBuild/scalaVersion := "2.13.2"
ThisBuild/organization := "com.mingyuanlu"

val scalaTest = "org.scalatest" %% "scalatest" % "3.1.2"
//val gigahorse = "com.eed3si9n" %% "gigahorse-okhttp" % "0.5.0"
//val playJason = "com.typesafe.play" %% "play-json" % "2.8.1" 

lazy val smart401kCalculator = (project in file("."))
   .aggregate(util)
   .dependsOn(util)
   .enablePlugins(JavaAppPackaging)
   .settings(
      name := "Smart401kCalculator",
      //libraryDependencies += "com.typesafe.play" %% "play-json" % "2.8.1",
      //libraryDependencies += "com.eed3si9n" %% "gigahorse-okhttp" % "0.5.0",
      //libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.2" % Test,      
      libraryDependencies += scalaTest % Test
   )

lazy val exceptions = (project in file("exceptions"))
   .settings(
      name := "exceptions",
      //libraryDependencies ++= Seq(gigahorse, playJason),
      libraryDependencies += scalaTest % Test
   )

lazy val constants = (project in file("constants"))
   .settings(
      name := "constants",
      libraryDependencies += scalaTest % Test
   )

lazy val util = (project in file("util"))
   .aggregate(exceptions, constants)
   .dependsOn(exceptions, constants)
   .settings(
      name := "util",
      libraryDependencies += scalaTest % Test
   )

