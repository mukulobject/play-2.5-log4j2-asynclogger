import com.google.common.collect.ImmutableMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import play.api.Environment;
import play.api.LoggerConfigurator;
import play.api.Mode;
import scala.Enumeration;
import scala.Option;
import scala.Predef;
import scala.Tuple2;
import scala.collection.JavaConverters;
import scala.collection.immutable.Map;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Log4J2LoggerConfigurator implements LoggerConfigurator {
    @Override
    public void init(File rootPath, Enumeration.Value mode) {
        java.util.Map<String, String> hm = ImmutableMap.<String, String>builder().put("application.home",
                rootPath.getAbsolutePath()).build();

        Map properties = JavaConverters.mapAsScalaMapConverter(hm).asScala().toMap(
                Predef.<Tuple2<String, String>>conforms()
        );

        String resourceName = mode == Mode.Dev() ? "log4j2-dev.xml" : "log4j2.xml";
        Option<URL> resourceUrl = Option.apply(this.getClass().getClassLoader().getResource(resourceName));
        configure(properties, resourceUrl);
    }

    @Override
    public void configure(Environment env) {
        java.util.Map<String, String> hm = ImmutableMap.<String, String>builder().put("application.home",
                env.rootPath().getAbsolutePath()).build();

        Map properties = JavaConverters.mapAsScalaMapConverter(hm).asScala().toMap(
                Predef.<Tuple2<String, String>>conforms()
        );
        Option<URL> resourceUrl = env.resource("log4j2.xml");
        configure(properties, resourceUrl);
    }

    @Override
    public void configure(Map<String, String> properties, Option<URL> config) {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        try {
            context.setConfigLocation(config.get().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {
        LoggerContext context = (LoggerContext) LogManager.getContext();
        Configurator.shutdown(context);
    }
}
