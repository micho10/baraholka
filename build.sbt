organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.4"

val macwire   = "com.softwaremill.macwire" %% "macros"    % "2.3.0" % "provided"
val scalaTest = "org.scalatest"            %% "scalatest" % "3.0.4" % Test

lazy val `hello-lagom` = (project in file("."))
  .aggregate(
    `bf-lagom-api`,
    `bf-lagom-impl`,
    `hello-lagom-api`,
    `hello-lagom-impl`,
    `hello-lagom-stream-api`,
    `hello-lagom-stream-impl`,
  )

lazy val `bf-lagom-api` = (project in file("bf-lagom-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `bf-lagom-impl` = (project in file("bf-lagom-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`bf-lagom-api`)



lazy val `hello-lagom-api` = (project in file("hello-lagom-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `hello-lagom-impl` = (project in file("hello-lagom-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`hello-lagom-api`)

lazy val `hello-lagom-stream-api` = (project in file("hello-lagom-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `hello-lagom-stream-impl` = (project in file("hello-lagom-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`hello-lagom-stream-api`, `hello-lagom-api`)
