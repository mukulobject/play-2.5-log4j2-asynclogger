
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/mukul/work/Projects/Experimental/play-2.5-log4j2-asynclogger/conf/routes
// @DATE:Tue Apr 05 11:03:08 PDT 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
