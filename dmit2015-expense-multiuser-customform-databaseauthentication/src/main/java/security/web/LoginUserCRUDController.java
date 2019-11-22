package security.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import security.entity.LoginGroup;
import security.entity.LoginUser;
import security.service.LoginUserBean;

@Named
@ViewScoped
public class LoginUserCRUDController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	
	@EJB
	private LoginUserBean loginUserBean;
	
	/** The current LoginUser to create, edit, update or delete */
	@Produces
	@Named
	private LoginUser loginUserDetail = new LoginUser();

	@Getter @Setter
	@NotBlank(message="A user must be assigned to at least one group")
	private String selectedGroups;								// +getter +setter
	
	@Getter
	private List<LoginUser> loginUsers;
	
	@Getter @Setter
	private boolean editMode = false;
	
	@Getter @Setter
	private Long editId;
		
	@PostConstruct
	public void init() {
		try {
			loginUsers = loginUserBean.list();
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			Messages.addFlashGlobalInfo(e.getMessage());
			
			logger.warning("Access Exception: " + e.getMessage());
			String message = String.format("Remote IP: %s\n Remote User: %s\n", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(message);
		} catch(Exception e) {
			Messages.addGlobalInfo("Retrieve list of {0} was not successful.", LoginUser.class.getSimpleName());
		}
	}
	
	public String create() {
				
		String outcome = null;
		
		try {
			String[] groups = selectedGroups.split(",");
			loginUserBean.add(loginUserDetail, groups);
			loginUserDetail = new LoginUser();
			Messages.addFlashGlobalInfo("Create new {0} was successful", LoginUser.class.getSimpleName());
			outcome = "list?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			logger.warning("Access Exception: " + e.getMessage());
			String message = String.format("Remote IP: %s\n Remote User: %s\n", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(message);
		} catch(Exception e) {
			Messages.addGlobalInfo("Create new {0} was not successful.", LoginUser.class.getSimpleName());
		}
		
		return outcome;
	}

	public String update() {
		String outcome = null;
		
		try {
			String[] groups = selectedGroups.split(",");
			loginUserBean.update(loginUserDetail, groups);
			loginUserDetail = new LoginUser();
			editMode = false;
			editId = null;
			Messages.addFlashGlobalInfo("Update existing {0} was successful", LoginUser.class.getSimpleName());
			outcome = "list?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			logger.warning("Access Exception: " + e.getMessage());
			String message = String.format("Remote IP: %s\n Remote User: %s\n", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(message);	
		} catch(Exception e) {
			Messages.addGlobalInfo("Update existing {0} was not successful.", LoginUser.class.getSimpleName());
		}
		
		return outcome;
	}

	public String delete() {
		String outcome = null;
		
		try {
			loginUserBean.delete(loginUserDetail);
			loginUsers.remove(loginUserDetail);
			loginUserDetail = null;
			editMode = false;
			editId = null;
			Messages.addFlashGlobalInfo("Delete existing {0} was successful", LoginUser.class.getSimpleName());
			outcome = "list?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			logger.warning("Access Exception: " + e.getMessage());
			String message = String.format("Remote IP: %s\n Remote User: %s\n", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(message);
		} catch(Exception e) {
			Messages.addGlobalInfo("Delete existing {0} was not successful.", LoginUser.class.getSimpleName());
		}
		
		return outcome;
	}

	public void edit() {
		if (!Faces.isPostback() && !Faces.isValidationFailed() ) {
			if (editId != null) {
				try {
					loginUserDetail = loginUserBean.findOneById(editId);
					if (loginUserDetail != null) {
						editMode = true;
					 	selectedGroups = "";
						for(LoginGroup group : loginUserDetail.getGroups()) {
							selectedGroups += group.getGroupname() + ",";
					 	}
						
					} else {
						Messages.addFlashGlobalWarn("{0} is not a valid id value", editId);
						Faces.navigate("listEvents?faces-redirect=true");						
					}
				} catch (Exception e) {
					Messages.addGlobalInfo("Query by id was not successful");
				}	
			} else {
				Faces.navigate("list?faces-redirect=true");	
			}
		} 
	}
	
	public String cancel() {
		loginUserDetail = null;
		editMode = false;
		return "list?faces-redirect=true";
	}
	
	@Getter @Setter
	@NotBlank(message="Current Password field value is required")
	private String currentPassword;	
	
	public String changePassword() {
		String nextUrl = null;
		try {
			if (loginUserDetail.getPlainTextPassword().equals(loginUserDetail.getConfirmedPlainTextPassword())) {
				String currentUsername = Faces.getRemoteUser();
				loginUserBean.changePassword(currentUsername, currentPassword, loginUserDetail.getPlainTextPassword());
				Messages.addFlashGlobalInfo("Change password was successful.");
				nextUrl = "/index?faces-redirect=true";				
			} else {
				Messages.addGlobalInfo("New password must match Confirmed new password.");
			}
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			logger.warning("Access Exception: " + e.getMessage());
			String message = String.format("Remote IP: %s\n Remote User: %s\n", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(message);
		} catch(Exception e) {
			Messages.addGlobalInfo(e.getMessage());
		}
		return nextUrl;
	}
}
