package controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final Logger asyncLogger = LogManager.getLogger("ASYNC");

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        ThreadContext.put("requestId", "unique_request_id_123456");
        asyncLogger.debug("async debug here ...");
        ThreadContext.clearAll();
        return ok(index.render("Your new application is ready."));
    }

}
