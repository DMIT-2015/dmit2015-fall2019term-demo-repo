package expense.resource;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("webapi")
@DeclareRoles({"USER","ADMIN"})
public class JAXRSConfiguration extends Application {

}