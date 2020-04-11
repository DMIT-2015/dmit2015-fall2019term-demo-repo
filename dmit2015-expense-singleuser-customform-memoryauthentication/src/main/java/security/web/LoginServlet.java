package security.web;

import java.io.IOException;
import java.util.Base64;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
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
    
	public static final String LOGIN_ATTEMPTS_SESSION_ATTRIBUTE = "loginAttempts";
	public static final int MAX_ATTEMPTS_ALLOWED = 5;
	
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
			response.setContentType("application/json;charset=UTF-8");
			String message = String.format("You %s are banned from this site", username);
			JsonObject responseBodyData = Json.createObjectBuilder()
					.add("message", message)
					.build();
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getWriter().println(responseBodyData);
		} else {
			switch (continueAuthentication(request, response, username, password, isCallerInitiatedAuthentication)) {
				case SEND_CONTINUE: {
					System.out.println("Login SEND_CONTINUE");
				}
					break;
				case SEND_FAILURE: {
	 				System.out.println("Login SEND_FAILURE");
					loginAttempts += 1;
					session.setAttribute(LOGIN_ATTEMPTS_SESSION_ATTRIBUTE, loginAttempts);
					String message = String.format("Incorrect login credentials. Login attempt #%d", loginAttempts);
					response.setContentType("application/json;charset=UTF-8");
					JsonObject responseBodyData = Json.createObjectBuilder()
						.add("message", message)
						.build();
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getWriter().println(responseBodyData);
				}
					break;
				case SUCCESS: {
					System.out.println("Login SUCCESS");
					String plainTextCredential = String.format("%s:%s",  username, password);
					String encodedCredential = String.format("Basic %s", Base64.getEncoder().encodeToString(plainTextCredential.getBytes()));
					response.setContentType("application/json;charset=UTF-8");
					JsonObject responseBodyData = Json.createObjectBuilder()
						.add("sessionId", session.getId())
						.add("Authorization", encodedCredential)
						.build();
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().println(responseBodyData);
				}	
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
