package common.resource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = 
	@Info(
		title = "User and Expense API",
		version = "1.0",
		description = "RESTful services for managing users and their expenses",
		contact = @Contact(
			name = "Your Name",
			email = "yourUsername@nait.ca",
			url = "https://www.nait.ca/programs/dmit-computer-software-development?term=2020-winter"
		)
	),
	servers = {
		@Server(description = "Development Server", url = "https://localhost:8443/dmit2015-expense-multiuser-customform-customauthentication"),
		@Server(description = "Production Server", url = "https://dmit-dev1.nait.ca:8443/dmit2015-expense-multiuser-customform-customauthentication"),		
	}
)
@ApplicationPath("api")
public class JaxRsActivator extends Application {
	
}
