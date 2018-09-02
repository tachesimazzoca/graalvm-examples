package app

import org.graalvm.polyglot.Value

case class Config(env: String, app: AppConfig)

case class AppConfig(val hostname: String, val port: Int)

object Config {
  def fromPolyglot(v: Value): Config = {
    val app = v.getMember("app")
    val appConfig = AppConfig(
      app.getMember("host").asString(),
      app.getMember("port").asInt())
    Config(v.getMember("env").asString(), appConfig)
  }
}
