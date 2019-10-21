package dmit2015.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;

@DataSourceDefinition(
	name="java:app/datasources/dmit2015-demo/demoDS",
	className="com.microsoft.sqlserver.jdbc.SQLServerDataSource",
	url="jdbc:sqlserver://localhost;databaseName=Northwind",
	user="user2015",
	password="Password2015")

@ApplicationScoped
public class ApplicationConfig {

}