package security.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet is used to login in a user when using Jakarta EE Security to perform custom authentication.
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final String LOGIN_ATTEMPTS_SESSION_ATTRIBUTE = "loginAttempts";
	private static final int MAX_ATTEMPTS_ALLOWED = 5;
	
	@Inject
	private SecurityContext securityContext;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		boolean isCallerInitiatedAuthentication = (request.getParameter("isNew") != null);

		int loginAttempts = 0;
		HttpSession session = request.getSession();
		if (session.getAttribute(LOGIN_ATTEMPTS_SESSION_ATTRIBUTE) != null) {
			loginAttempts = (int) session.getAttribute(LOGIN_ATTEMPTS_SESSION_ATTRIBUTE);
		}

		if (loginAttempts >= MAX_ATTEMPTS_ALLOWED) {
			String message = String.format("You %s are banned from this site", username);
			response.getWriter().println(message);
		} else {
			switch (continueAuthentication(request, response, username, password, isCallerInitiatedAuthentication)) {
				case SEND_CONTINUE:
					System.out.println("Login SEND_CONTINUE");
					break;
				case SEND_FAILURE:
	 				System.out.println("Login SEND_FAILURE");
					loginAttempts += 1;
					session.setAttribute(LOGIN_ATTEMPTS_SESSION_ATTRIBUTE, loginAttempts);
					String message = String.format("Incorrect login credentials. Login attempt #%d", loginAttempts);
					response.sendRedirect("/login/login.html?error=" + URLEncoder.encode(message, "UTF-8"));
					break;
				case SUCCESS:
					System.out.println("Login SUCCESS");
					response.sendRedirect("/index.xhtml");
					break;
				case NOT_DONE:
					System.out.println("Login NOT_DONE");
					break;
			}	
		}
		
	}

	private AuthenticationStatus continueAuthentication(
		HttpServletRequest request, 
		HttpServletResponse response, 
		String username, 
		String password,
		boolean isNew) {
		
		Credential credential = new UsernamePasswordCredential(username, new Password(password) );		
		return securityContext.authenticate(request, response, 
				AuthenticationParameters.withParams()
					.newAuthentication(isNew)	// added for Caller-Initiated Authentication
					.credential(credential));
	}
}
