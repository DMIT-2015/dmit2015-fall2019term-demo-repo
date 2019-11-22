package expense.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

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
	@Credentials(callerName = "user2015", password = "Password2015", groups = { "USER", "ADMIN", "DEVELOPER" }),
	@Credentials(callerName = "dmit2015", password = "Password2015", groups = "USER"),
	@Credentials(callerName = "admin2015", password = "Password2015", groups = "ADMIN"), 
})

@DatabaseIdentityStoreDefinition(
	dataSourceLookup="java:app/datasources/dmit2015-expense-multiuser-customform-databaseauthentication/h2DS",
	callerQuery="SELECT password FROM LoginUser WHERE username = ?",
	groupsQuery="SELECT g.groupname FROM LoginUser u, LoginUserGroup ug, LoginGroup g WHERE u.username = ? AND u.id = ug.userid AND ug.groupid = g.id",
	priority = 5
)

@DataSourceDefinitions({
	@DataSourceDefinition(
		name = "java:app/datasources/dmit2015-expense-multiuser-customform-databaseauthentication/h2DS", 
		className = "org.h2.jdbcx.JdbcDataSource", 
		url = "jdbc:h2:mem:expensedb", 
		user = "sa", 
		password = "sa"), 
})

@FacesConfig(version = Version.JSF_2_3)
@ApplicationScoped
public class ApplicationConfig {

}