package app

import org.graalvm.polyglot.Context
import org.scalatest.{FunSuite, Matchers}

class ConfigSuite extends FunSuite with Matchers {

  private def polyglotValue(languageId: String, source: String) =
    Context.newBuilder(languageId).allowAllAccess(true).build().eval(languageId, source)

  test("js") {

    val configSource =
      """function(name) {
        |  if (typeof(name) !== 'string' || name === '') {
        |    name = 'development';
        |  };
        |  return {
        |     env: name,
        |     app: {
        |       host: 'www.example.net',
        |       port: 8080
        |     }
        |  }
        |}""".stripMargin

    val configLang = "js"

    val f = polyglotValue(configLang, configSource)
    val config = Config.fromPolyglot(f.execute(""))

    config.env should ===("development")
    config.app should ===(AppConfig("www.example.net", 8080))
  }

  test("ruby") {

    val configSource = """
        |def config(name)
        |  env = name.empty? ? 'development' : name
        |  {
        |    'env' => env,
        |    'app' => {
        |      'host' => 'www.example.net',
        |      'port' => 8080
        |    }
        |  }
        |end""".stripMargin

    val configLang = "ruby"

    val f = polyglotValue(configLang, configSource).getMetaObject.getMember("config")
    val config = Config.fromPolyglot(f.execute("production"))

    config.env should ===("production")
    config.app should ===(AppConfig("www.example.net", 8080))
  }
}
