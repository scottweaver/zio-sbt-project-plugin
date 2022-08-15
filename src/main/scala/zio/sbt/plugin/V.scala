package zio.sbt.plugin

import Console._

object V {
  val scala213Version        = "2.13.8"
  val scala212Version        = "2.12.16"
  val scala3Version          = "3.1.3"
  val supportedScalaVersions = List(scala213Version, scala212Version, scala3Version)
  val zio1xVersion           = "1.0.15"
  val zio2xVersion           = "2.0.0"

  private val versions: Map[String, String] = {

    try {
      import org.snakeyaml.engine.v2.api.{Load, LoadSettings}

      import java.util.{List => JList, Map => JMap}
      import scala.jdk.CollectionConverters._

      val doc = new Load(LoadSettings.builder().build())
        .loadFromReader(scala.io.Source.fromFile(".github/workflows/ci.yml").bufferedReader())
      val yaml = doc.asInstanceOf[JMap[String, JMap[String, JMap[String, JMap[String, JMap[String, JList[String]]]]]]]
      val list = yaml.get("jobs").get("test").get("strategy").get("matrix").get("scala").asScala
      list.map(v => (v.split('.').take(2).mkString("."), v)).toMap
    } catch {
      case e: Exception =>
        println(s"${YELLOW}${BOLD} >>>> Failed to read '.github/workflows/ci.yml'.  Falling back to '2.12.8'/'3.1.3'  Cause: ${e} ${RESET}")
        Map.empty
    }
  }

  val Scala212: String = versions.getOrElse("2.12", "2.12.16")
  val Scala213: String = versions.getOrElse("2.13", "2.13.8")
  val Scala3: String   = versions.getOrElse("3.1", "3.1.3")
}
