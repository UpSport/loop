package upsport.loop.rest;

import static java.lang.Runtime.getRuntime;
import static java.lang.System.exit;
import static java.net.URI.create;
import static javax.ws.rs.core.UriBuilder.fromUri;
import static org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory.createHttpServer;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilderException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger("main");

	// Base URI the Grizzly HTTP server will listen on
	public static final int PORT = 1979;
	public static final String BASE_URI = "http://0.0.0.0:" + PORT;

	public static void main(String[] args) throws Exception {
		new Main(false).startServer(args);
	}

	private final boolean isDevMode;
	private HttpServer server;

	public Main(boolean isDevMode) {
		this.isDevMode = isDevMode;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public void startServer(String[] args) {
		LOG.info("Configuring Server");

		URI uri = create(BASE_URI);

		LOG.info("Creating Server");
		// create an instance of grizzly http server
		server = createHttpServer(uri, new ApiApplication());

		server.getListener("grizzly").getFileCache().setEnabled(false);

		if (args != null && args.length > 0) {
			String adminPath = args[0];
			LOG.info("Admin Path => {}", adminPath);
			server.getServerConfiguration().addHttpHandler(
					new StaticHttpHandler(adminPath), "/page");
		}

		try {
			LOG.info("Starting Server");
			// start the grizzly http server
			server.start();

			LOG.info("Server Started at => {}", fromUri(BASE_URI).port(PORT)
					.build());

			GrizzlyHttpContainer handler = server.getServerConfiguration()
					.getHttpHandlersWithMapping().entrySet().stream()
					.map(p -> p.getKey()).findFirst()
					.map(GrizzlyHttpContainer.class::cast).get();

			ServiceLocator locator = handler.getApplicationHandler()
					.getServiceLocator();
			// UserBusiness userBusiness =
			// locator.getService(UserBusiness.class);
		} catch (IllegalArgumentException | UriBuilderException | IOException e) {
			LOG.error("Server could not be started", e);
			exit(1);
		}

		// if we are not in test mode then register a shutdown hook and join
		// the main thread to prevent the server from shutting down.
		if (isDevMode) {
			// for dev mode simply shut down the server.
			if (server.isStarted()) {
				server.shutdown();
			}
		} else {
			// for production mode wait for SIGINT signal and let the shutdown
			// hook take care of shutting the server down.

			// create a shutdow hook thread to stop the server.
			Thread shutdownHook = new Thread(() -> {
				LOG.info("Stopping Server");
				if (server.isStarted()) {
					server.shutdown();
				}
			});
			// register shutdown hook
			getRuntime().addShutdownHook(shutdownHook);
			try {
				Thread.currentThread().join();
			} catch (InterruptedException e) {
				// expected exception. NO-OP.
			}
		}
	}

	public boolean isStarted() {
		return server.isStarted();
	}

	public void shutdown() {
		server.shutdown();
	}

}
