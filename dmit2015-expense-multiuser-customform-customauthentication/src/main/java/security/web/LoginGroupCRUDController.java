package security.web;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import security.entity.LoginGroup;
import security.service.LoginGroupBean;

@Named
@ViewScoped
public class LoginGroupCRUDController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;
	
	@EJB
	private LoginGroupBean loginGroupBean;
	
	/** The current LoginGroup to create, edit, update or delete */
	@Produces
	@Named
	private LoginGroup loginGroupDetail = new LoginGroup();
	
	@Getter
	private List<LoginGroup> loginGroups;
	
	@Getter @Setter
	private boolean editMode = false;
	
	@Getter @Setter
	private Long editId;
		
	@PostConstruct
	public void init() {
		try {
			loginGroups = loginGroupBean.list();
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			
			String systemMessage = String.format("Unauthorized access from IP %s and user %s", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(systemMessage);
		} catch(Exception e) {
			Messages.addGlobalInfo("Retrieve list of {0} was not successful.", LoginGroup.class.getSimpleName());
		}
	}
	
	public String create() {
		String outcome = null;
		
		try {
			loginGroupBean.add(loginGroupDetail.getGroupname());
			loginGroupDetail = new LoginGroup();
			Messages.addFlashGlobalInfo("Create new {0} was successful", LoginGroup.class.getSimpleName());
			outcome = "list?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			
			String systemMessage = String.format("Unauthorized access from IP %s and user %s", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(systemMessage);
		} catch(Exception e) {
			Messages.addGlobalInfo("Create new {0} was not successful.", LoginGroup.class.getSimpleName());
		}
		
		return outcome;
	}

	public String update() {
		String outcome = null;
		
		try {
			loginGroupBean.update(loginGroupDetail);
			loginGroupDetail = new LoginGroup();
			editMode = false;
			editId = null;
			Messages.addFlashGlobalInfo("Update existing {0} was successful", LoginGroup.class.getSimpleName());
			outcome = "list?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			
			logger.warning("Access Exception: " + e.getMessage());
			String message = String.format("Remote IP: %s\n Remote User: %s\n", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(message);
		} catch(Exception e) {
			Messages.addGlobalInfo("Update existing {0} was not successful.", LoginGroup.class.getSimpleName());
		}
		
		return outcome;
	}

	public String delete() {
		String outcome = null;
		
		try {
			loginGroupBean.delete(loginGroupDetail);
			loginGroups.remove(loginGroupDetail);
			loginGroupDetail = null;
			editMode = false;
			editId = null;
			Messages.addFlashGlobalInfo("Delete existing {0} was successful", LoginGroup.class.getSimpleName());
			outcome = "list?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			logger.warning("Access Exception: " + e.getMessage());
			String message = String.format("Remote IP: %s\n Remote User: %s\n", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(message);
		} catch(Exception e) {
			Messages.addGlobalInfo("Delete existing {0} was not successful.", LoginGroup.class.getSimpleName());
		}
		
		return outcome;
	}

	public void edit() {
		if (!Faces.isPostback() && !Faces.isValidationFailed() ) {
			if (editId != null) {
				try {
					loginGroupDetail = loginGroupBean.findOneById(editId);
					if (loginGroupDetail != null) {
						editMode = true;
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
		loginGroupDetail = null;
		editMode = false;
		return "list?faces-redirect=true";
	}
}
