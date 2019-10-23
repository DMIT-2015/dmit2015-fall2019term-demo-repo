package northwind.controller;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import northwind.entity.Shipper;
import northwind.service.NorthwindService;

@Named
@ViewScoped
public class ShipperCRUDController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ShipperCRUDController.class.getName());
	
	@Inject
	private NorthwindService northwindService;
	
	private List<Shipper> shippers;					// +getter
	
	/** The current Shipper to create, edit, update, or delete */
	@Getter @Setter 
	private  Shipper shipperDetail = new Shipper();	// +getter +setter	
	
	@Getter @Setter 
	private boolean editMode = false;
	
	@Getter @Setter 
	private Integer editId = null;
	
	@PostConstruct
	void init() {
		try {
			shippers = northwindService.findAllShipper();
		} catch(Exception e) {
			Messages.addGlobalError("Error retreiving shippers");
			log.fine(e.getMessage());
		}
	}
	
	public String create() {
		String outcome = null;
		try {
			northwindService.createShipper(shipperDetail);
			shipperDetail = new Shipper();
			Messages.addFlashGlobalInfo("Create was succesful");
			outcome = "list?faces-redirect=true";
		} catch(Exception e) {
			Messages.addGlobalError("Create was not succesful");
			log.fine(e.getMessage());
		}
		
		return outcome;
	}
	
	public String update() {
		String outcome = null;
		try {
			northwindService.updateShipper(shipperDetail);
			shipperDetail = new Shipper();
			editMode = false;
			editId = null;
			Messages.addFlashGlobalInfo("Update was succesful");
			outcome = "list?faces-redirect=true";
		} catch(Exception e) {
			Messages.addGlobalError("Update was not successful");
			log.fine(e.getMessage());
		}
		
		return outcome;
	}
	
	public void delete(Shipper existingShipper) {
		try {
			northwindService.deleteShipper(existingShipper);
			shippers.remove(existingShipper);
			Messages.addGlobalInfo("Delete was successful");
		} catch (Exception e) {
			Messages.addGlobalError("Delete was not successful");
			log.fine(e.getMessage());
		}
	}

	public String delete() {
		String nextUrl = null;
		try {
			northwindService.deleteShipper(shipperDetail);
			shippers.remove(shipperDetail);
			shipperDetail = null;
			editId = null;
			Messages.addFlashGlobalInfo("Delete was successful");			
			nextUrl = "list?faces-redirect=true";
		} catch (Exception e) {
			Messages.addGlobalError("Delete was not successful");			
			log.fine(e.getMessage());
		}
		return nextUrl;
	}
	
	
	public void edit() {
		if (!Faces.isPostback() && !Faces.isValidationFailed() ) {
			if (editId != null) {
				try {
					shipperDetail = northwindService.findOneShipper(editId);
					if (shipperDetail != null) {
						editMode = true;
					} else {
						Messages.addFlashGlobalError("{0} is not a valid id value", editId);
						Faces.navigate("list?faces-redirect=true");						
					}
				} catch (Exception e) {
					Messages.addGlobalError("Query unsucessful");
					log.fine(e.getMessage());	
				}	
			} else {
				Faces.navigate("list?faces-redirect=true");	
			}
		} 
	}
	
	public String cancel() {
		shipperDetail = null;
		editMode = false;
		return "list?faces-redirect=true";
	}
	
	
	public List<Shipper> getShippers() {
		return shippers;
	}	
}