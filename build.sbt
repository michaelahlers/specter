lazy val PhantomRuntime =
  (project in file("modules") / "phantom-runtime")
    .enablePlugins(BuildInfoPlugin)
    .settings(
      buildInfoPackage := "consulting.ahlers.specter",
      buildInfoObject := "RuntimeBuildInfo",
      Settings.compiler,
      Settings.metadata
    )

lazy val SpecterAPI =
  (project in file("modules") / "specter-api")
    .enablePlugins(BuildInfoPlugin)
    .settings(
      buildInfoPackage := "consulting.ahlers.specter",
      buildInfoObject := "APIBuildInfo",
      publishArtifact in Test := true,
      Settings.compiler,
      Settings.metadata
    )
    .dependsOn(PhantomRuntime % "test->test;compile->compile")

lazy val Specter =
  (project in file("."))
    .aggregate(PhantomRuntime, SpecterAPI)
    .settings(
      Settings.metadata
    )
