package expense.config;

import javax.annotation.security.DeclareRoles;
import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

import org.glassfish.soteria.identitystores.annotation.Credentials;
import org.glassfish.soteria.identitystores.annotation.EmbeddedIdentityStoreDefinition;

@BasicAuthenticationMechanismDefinition(realmName = "jaspitest")

@EmbeddedIdentityStoreDefinition({
	@Credentials(callerName = "user2015", password = "Password2015", groups = { "USER" }),
	@Credentials(callerName = "dmit2015", password = "Password2015", groups = { "USER", "ADMIN" }),
	@Credentials(callerName = "admin2015", password = "Password2015", groups = "ADMIN"), 
})

@DataSourceDefinitions({
	@DataSourceDefinition(
		name = "java:app/datasources/dmit2015-expenseapp/h2DS", 
		className = "org.h2.jdbcx.JdbcDataSource", 
		url = "jdbc:h2:file:~/singleuser-expensedb", 
		user = "sa", 
		password = "sa"), 
})

@ApplicationScoped
@DeclareRoles({"USER","ADMIN"})
public class ApplicationConfig {

}