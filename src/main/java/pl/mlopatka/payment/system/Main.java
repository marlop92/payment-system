package pl.mlopatka.payment.system;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import static org.eclipse.jetty.servlet.ServletContextHandler.NO_SESSIONS;


public class Main {

    private static final int PORT = 8080;
    private static final String CONTEXT_PATH = "/";
    private static final int INIT_ORDER = 0;
    private static final String JERSEY_PACKAGES = "jersey.config.server.provider.packages";
    private static final String REST_CONTROLLERS = "pl.mlopatka.payment.system";
    private static final int EXIT_VAL = 1;
    private static final String SERVLETS_PATTERN = "/api/*";

    public static void main(String[] args) {
        Server server = new Server(PORT);

        ServletContextHandler servletContextHandler = new ServletContextHandler(NO_SESSIONS);

        servletContextHandler.setContextPath(CONTEXT_PATH);
        server.setHandler(servletContextHandler);

        ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, SERVLETS_PATTERN);
        servletHolder.setInitOrder(INIT_ORDER);
        servletHolder.setInitParameter(JERSEY_PACKAGES, REST_CONTROLLERS);

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            System.exit(EXIT_VAL);
        } finally {
            server.destroy();
        }
    }
}
