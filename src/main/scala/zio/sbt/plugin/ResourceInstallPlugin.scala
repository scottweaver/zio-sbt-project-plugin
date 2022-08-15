package zio.sbt.plugin

import sbt._
import sbt.Keys._
import java.nio.file._

object ResourceInstallPlugin extends AutoPlugin {

  override def trigger = allRequirements

  private def copyResource(source: String, target: String, baseDirectory: File, logger: String => Unit) = {

    val srcScalafmt = getClass.getResourceAsStream("")

    try {
      val targetScalafmtPath = (baseDirectory / target).toPath
      Files.copy(srcScalafmt, targetScalafmtPath)
    } catch {
      case e: Throwable =>
        logger(s"Failed to copy '${source}' to '${target}' .  Cause: ${e}:'${e.getMessage}'")
    } finally {

      try {
        srcScalafmt.close()
      } finally {
        ()
      }
    }
  }

  val copyZioDevSettings =
    taskKey[Unit]("Copies the provided .scalafmt.conf file and .scalafix.conf file to the project root.")

  val copySettingsImpl = Def.task[Unit] {
    copyResource("/scalafmt.conf", ".scalafmt.conf", baseDirectory.value, streams.value.log.error(_))
    copyResource("/scalafix.conf", ".scalafix.conf", baseDirectory.value, streams.value.log.error(_))
    copyResource("/gitignore", ".gitignore", baseDirectory.value, streams.value.log.error(_))
  }

  override def buildSettings: Seq[Setting[_]] = Seq(
    copyZioDevSettings := copySettingsImpl.value
  )

}
