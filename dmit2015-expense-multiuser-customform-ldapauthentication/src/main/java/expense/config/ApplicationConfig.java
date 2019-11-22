package expense.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.LdapIdentityStoreDefinition;

import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

@CustomFormAuthenticationMechanismDefinition(
	loginToContinue = @LoginToContinue(
		loginPage="/security/customLogin.xhtml", 
		useForwardToLogin = false,
		errorPage=""
	)
)

@EmbeddedIdentityStoreDefinition({
	@Credentials(callerName = "user2015", password = "Password2015", groups = { "USER", "ADMIN" }),
	@Credentials(callerName = "dmit2015", password = "Password2015", groups = "USER"),
	@Credentials(callerName = "admin2015", password = "Password2015", groups = "ADMIN"), 
})

@LdapIdentityStoreDefinition(
	url = "ldap://metro-ds1.nait.ca:389",
	callerSearchBase = "dc=nait,dc=ca",
	callerNameAttribute = "SamAccountName",	// SamAccountName or UserPrincipalName
	groupSearchBase = "dc=nait,dc=ca",
	bindDn = "cn=DMIT Student1,ou=DMITStudentRestricted,ou=Student,ou=DMIT,ou=SICET,dc=nait,dc=ca",
	bindDnPassword = "Password2015",
	priority = 5
)

@DataSourceDefinitions({
	@DataSourceDefinition(
		name = "java:app/datasources/dmit2015-expenseapp/h2DS", 
		className = "org.h2.jdbcx.JdbcDataSource", 
		url = "jdbc:h2:file:~/multiuser-ldap-expensedb", 
		user = "sa", 
		password = "sa"), 
})

@FacesConfig(version = Version.JSF_2_3)
@ApplicationScoped
public class ApplicationConfig {

}