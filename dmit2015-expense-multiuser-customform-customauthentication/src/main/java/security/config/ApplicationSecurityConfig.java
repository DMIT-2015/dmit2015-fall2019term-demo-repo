package security.config;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.LdapIdentityStoreDefinition;

import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

//@FormAuthenticationMechanismDefinition(		
//	loginToContinue = @LoginToContinue(
//		loginPage="/security/login.xhtml",
//		errorPage="/security/login.xhtml?error=true",
//		useForwardToLogin = false
//	)
//)

@CustomFormAuthenticationMechanismDefinition(
	loginToContinue = @LoginToContinue(
		loginPage="/security/customLogin.xhtml", 
		useForwardToLogin = false,
		errorPage=""
	)
)

@EmbeddedIdentityStoreDefinition({
	@Credentials(
		callerName = "user2015",
		password = "Password2015",
		groups = { "USER","ADMIN" }
	),
	@Credentials(
		callerName = "dmit2015",
		password = "Password2015",
		groups = { "USER" }
	)
})

//@LdapIdentityStoreDefinition(
//	url = "ldap://metro-ds1.nait.ca:389",
//	callerSearchBase = "dc=nait,dc=ca",
//	callerNameAttribute = "SamAccountName",	// SamAccountName or UserPrincipalName
//	groupSearchBase = "dc=nait,dc=ca",
//	bindDn = "cn=DMIT Student1,ou=DMITStudentRestricted,ou=Student,ou=DMIT,ou=SICET,dc=nait,dc=ca",
//	bindDnPassword = "Password2015",
//	priority = 5
//)

//@DatabaseIdentityStoreDefinition(
//	dataSourceLookup="java:app/datasources/dmit2015-security-app/securityDS",
//	callerQuery="SELECT password FROM LoginUser WHERE username = ?",
//	groupsQuery="SELECT g.groupname FROM LoginUser u, LoginUserGroup ug, LoginGroup g WHERE u.username = ? AND u.id = ug.userid AND ug.groupid = g.id",
//	priority = 10
//)

@ApplicationScoped
public class ApplicationSecurityConfig {

}
