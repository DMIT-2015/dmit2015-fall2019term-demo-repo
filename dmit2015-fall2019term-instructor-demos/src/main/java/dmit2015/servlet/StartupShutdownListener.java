package dmit2015.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class StartupShutdownListener
 *
 */
@WebListener
public class StartupShutdownListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("Servlet startup...");
		System.out.println(event.getServletContext().getServerInfo());
		System.out.println(System.currentTimeMillis());
		// See error in server.log if mail is unsuccessful
		sendEmail("Servlet context has initialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("Servlet shutdown...");
		System.out.println(event.getServletContext().getServerInfo());
		System.out.println(System.currentTimeMillis());
		// See error in server.log if mail is unsuccessful
		sendEmail("Servlet context has been destroyed...");
	}

	private boolean sendEmail(String message) {
		boolean result = false;
		
		// Add code to send an email to your own account

		return result;
	}
	
}
