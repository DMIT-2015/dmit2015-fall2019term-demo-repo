package expense.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
import javax.enterprise.context.ApplicationScoped;

@DataSourceDefinitions({
	@DataSourceDefinition(
			name="java:app/datasources/dmit2015-demo-expenseapp/h2DS",
			className="org.h2.jdbcx.JdbcDataSource",
			url="jdbc:h2:file:~/expensedb",
			user="sa",
			password="sa"),
})

@ApplicationScoped
public class ApplicationConfig {

}