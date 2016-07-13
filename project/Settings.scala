import sbt.Keys._

// TODO: Restore once IntelliJ catches up with SBT 1.0.
//import sbt.syntax._
import sbt._

import scala.language.postfixOps

object Settings {

  val compiler =
    List(

      scalaVersion := "2.11.8",

      /**
       * Strict settings to avoid common bugs. Class files are limited in length to support builds on Windows.
       */
      scalacOptions ++=
        "-feature" ::
          "-unchecked" ::
          "-deprecation" ::
          "-Xfatal-warnings" ::
          "-Xmax-classfile-name" :: "150" ::
          Nil,

      scalacOptions ++=
        (CrossVersion.partialVersion(scalaVersion.value) match {

          case Some((2, minor)) if 11 <= minor =>
            "-target:jvm-1.8" ::
              Nil

          case _ =>
            Nil

        })

    )

  val metadata =
    List(

      organization := "consulting.ahlers",

      homepage := Some(url("http://github.com/michaelahlers/specter")),

      startYear := Some(2016),

      developers :=
        Developer("michaelahlers", "Michael Ahlers", "michael@ahlers.consulting", url("http://github.com/michaelahlers")) ::
          Nil,

      scmInfo :=
        Some(ScmInfo(
          browseUrl = url("http://github.com/michaelahlers/specter"),
          connection = "scm:git:https://github.com:michaelahlers/specter.git",
          devConnection = Some("scm:git:git@github.com:michaelahlers/specter.git")
        ))

    )

}
