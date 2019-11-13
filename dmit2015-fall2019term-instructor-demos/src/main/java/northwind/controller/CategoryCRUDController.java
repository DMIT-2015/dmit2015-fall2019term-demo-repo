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
import northwind.entity.Category;
import northwind.service.NorthwindService;

@Named
@ViewScoped
public class CategoryCRUDController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(CategoryCRUDController.class.getName());
	
	@Inject
	private NorthwindService northwindService;
	
	private List<Category> categorys;					// +getter
	
	/** The current Category to create, edit, update, or delete */
	@Getter @Setter 
	private  Category categoryDetail = new Category();	// +getter +setter	
	
	@Getter @Setter 
	private boolean editMode = false;
	
	@Getter @Setter 
	private Integer editId = null;
	
	@PostConstruct
	void init() {
		try {
			categorys = northwindService.findAllCategory();
		} catch(Exception e) {
			Messages.addGlobalError("Error retreiving categorys");
			log.fine(e.getMessage());
		}
	}
	
	public String create() {
		String outcome = null;
		try {
			northwindService.createCategory(categoryDetail);
			categoryDetail = new Category();
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
			northwindService.updateCategory(categoryDetail);
			categoryDetail = new Category();
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
	
	public void delete(Category existingCategory) {
		try {
			northwindService.deleteCategory(existingCategory);
			categorys.remove(existingCategory);
			Messages.addGlobalInfo("Delete was successful");
		} catch (Exception e) {
			Messages.addGlobalError("Delete was not successful");
			log.fine(e.getMessage());
		}
	}

	public String delete() {
		String nextUrl = null;
		try {
			northwindService.deleteCategory(categoryDetail);
			categorys.remove(categoryDetail);
			categoryDetail = null;
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
					categoryDetail = northwindService.findOneCategory(editId);
					if (categoryDetail != null) {
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
		categoryDetail = null;
		editMode = false;
		return "list?faces-redirect=true";
	}
	
	
	public List<Category> getCategorys() {
		return categorys;
	}	
}