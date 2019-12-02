package security.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import security.entity.LoginUser;
import security.service.LoginUserBean;

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginUserResource {

	@Inject
	private LoginUserBean loginUserBean;
		
	@GET
	public List<LoginUser> findAll() {
		return loginUserBean.list();
	}
	
	@GET
	@Path("{id}")
	public LoginUser findByid(@PathParam("id") Long userId) {
		return loginUserBean.findOneById(userId);
	}
	
	@POST
	public void addLoginUser(LoginUser newLoginUser,  @QueryParam("groups") String[] groupNames) {
		loginUserBean.add(newLoginUser, groupNames);
	}
	
	@PUT
	public void updateLoginUser(LoginUser existingLoginUser, @QueryParam("groups") String[] groupNames) {
		loginUserBean.update(existingLoginUser, groupNames);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteLoginUser(@PathParam("id") Long userId) {
		LoginUser existingLoginUser = loginUserBean.findOneById(userId);
		if (existingLoginUser != null) {
			loginUserBean.delete(existingLoginUser);			
		}
	}
}
