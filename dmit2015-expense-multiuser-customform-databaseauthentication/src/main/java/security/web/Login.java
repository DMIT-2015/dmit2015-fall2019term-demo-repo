package security.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final int MAX_ATTEMPTS_ALLOWED = 3;
	
	@Getter
	private int loginAttempts = 0;

	@Inject
	private SecurityContext securityContext;
		
	@Inject @ManagedProperty("#{param.new}")
	private boolean isNew;	// added for Caller-Initiated Authentication
	
	@NotBlank(message="Username value is required.")
	@Getter @Setter
	private String username;
	
	@NotBlank(message="Password value is required.")
	@Getter @Setter
	private String password;

	public void submit() {
		if (loginAttempts >= MAX_ATTEMPTS_ALLOWED) {
			Faces.redirectPermanent(Faces.getRequestContextPath() + "/errorpages/403.xhtml");
		}
			
		switch (continueAuthentication()) {
			case SEND_CONTINUE:
				Faces.responseComplete();
				break;
			case SEND_FAILURE:
				loginAttempts += 1;
				Messages.addGlobalError("Login failed. Incorrect login credentials.");
				Messages.addGlobalError("Login attempt #{0}", loginAttempts);
				if (loginAttempts >= MAX_ATTEMPTS_ALLOWED) {
					Messages.addGlobalFatal("You {0} are banned from this site", username);
				}
				break;
			case SUCCESS:
				Faces.getFlash().setKeepMessages(true);
				Messages.addFlashGlobalInfo("Login succeed");
				Faces.redirect(Faces.getRequestContextPath() + "/index.xhtml");		// added for Caller-Initiated Authentication
				break;
			case NOT_DONE:
				// JSF does not need to take any special action here
				break;
		}				
	}
	
	private AuthenticationStatus continueAuthentication() {
		Credential credential = new UsernamePasswordCredential(username, new Password(password) );		
		HttpServletRequest request = Faces.getRequest();
		HttpServletResponse response = Faces.getResponse();
		return securityContext.authenticate(request, response, 
				AuthenticationParameters.withParams()
					.newAuthentication(isNew)	// added for Caller-Initiated Authentication
					.credential(credential));
	}
}
