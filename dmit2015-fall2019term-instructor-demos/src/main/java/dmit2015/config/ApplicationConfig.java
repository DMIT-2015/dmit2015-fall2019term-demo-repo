package dmit2015.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
import javax.enterprise.context.ApplicationScoped;

@DataSourceDefinitions({
	@DataSourceDefinition(
			name="java:app/datasources/dmit2015-demo/dmit2015DS",
			className="org.h2.jdbcx.JdbcDataSource",
			url="jdbc:h2:mem:dmit2015db",
			user="sa",
			password="sa"),
	@DataSourceDefinition(
		name="java:app/datasources/dmit2015-demo/northwindDS",
		className="com.microsoft.sqlserver.jdbc.SQLServerDataSource",
		url="jdbc:sqlserver://localhost;databaseName=Northwind",
		user="user2015",
		password="Password2015")
})

@ApplicationScoped
public class ApplicationConfig {

}