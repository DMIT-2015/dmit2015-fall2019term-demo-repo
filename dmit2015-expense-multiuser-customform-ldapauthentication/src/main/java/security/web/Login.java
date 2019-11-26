package security.web;

import java.io.Serializable;
import java.util.logging.Logger;

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

import org.apache.directory.api.ldap.model.cursor.SearchCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.message.Response;
import org.apache.directory.api.ldap.model.message.SearchRequest;
import org.apache.directory.api.ldap.model.message.SearchRequestImpl;
import org.apache.directory.api.ldap.model.message.SearchResultEntry;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final int MAX_ATTEMPTS_ALLOWED = 3;
	
	@Inject
	private Logger logger;
	
	@Getter
	private String displayName = "No Assigned Display Name";

	@Getter
	private String firstName = "No First Name";

	@Getter
	private String lastName = "No Last Name";

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
				queryLdapUserInfo();
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
				queryLdapUserInfo();
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
	
	private void queryLdapUserInfo() {
		// Fetch displayName and mail attribute name from LDAP server
		try {
			final String LDAP_SERVER = "metro-ds1.nait.ca";
			final String LDAP_BIND_CN = "cn=DMIT Student1,ou=DMITStudentRestricted,ou=Student,ou=DMIT,ou=SICET,dc=nait,dc=ca";
			final String LDAP_BIND_PASSWORD = "Password2015";
			final String LDAP_QUERY_DN = "OU=Accounts,dc=nait,dc=ca";
			
			LdapConnection connection = new LdapNetworkConnection(LDAP_SERVER);
			connection.bind(LDAP_BIND_CN, LDAP_BIND_PASSWORD);
			Dn queryDn = new Dn(LDAP_QUERY_DN);
			SearchRequest request = new SearchRequestImpl();
			request.setScope(SearchScope.SUBTREE);			
			request.addAttributes("*");
			request.setBase(queryDn);			
			request.setFilter(String.format("(cn=%s)", username));
			
			// Process the request
			SearchCursor searchCursor = connection.search(request);
			if (searchCursor.next()) {
				Response response = searchCursor.get();				
				if (response instanceof SearchResultEntry) {
					Entry resultEntry = ((SearchResultEntry) response).getEntry();
					displayName = resultEntry.get("displayName").getString();
					lastName = resultEntry.get("sn").getString();
					firstName = resultEntry.get("givenName").getString();
					
				} else {
					logger.info("No response from LDAP query");
				}				
			} else {
				logger.info("Username not found on LDAP server");
			}
			// Unbind
			connection.unBind();
			// Close the connection
			connection.close();			
		} catch (Exception  e) {
			logger.warning("Exception with LDAP query :" + e.getMessage());
		}
	}
}
