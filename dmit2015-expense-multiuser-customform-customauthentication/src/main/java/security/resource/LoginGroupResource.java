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

import security.entity.LoginGroup;
import security.service.LoginGroupBean;

@Path("groups")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginGroupResource {

	@Inject
	private LoginGroupBean loginGroupBean;
	
	@GET
	public List<LoginGroup> findAll() {
		return loginGroupBean.list();
	}
	
	@GET
	@Path("{id}")
	public LoginGroup findById(@PathParam("id") Long groupId) {
		return loginGroupBean.findOneById(groupId);
	}
	
	@POST
	public void addLoginGroup(@QueryParam("group") String groupname) {
		loginGroupBean.add(groupname);
	}
	
	@PUT
	public void updateLoginGroup(LoginGroup existingLoginGroup) {
		loginGroupBean.update(existingLoginGroup);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteLoginGroup(@PathParam("id") Long groupId) {
		LoginGroup existingLoginGroup = loginGroupBean.findOneById(groupId);
		if (existingLoginGroup != null) {
			loginGroupBean.delete(existingLoginGroup);			
		}
	}
}
