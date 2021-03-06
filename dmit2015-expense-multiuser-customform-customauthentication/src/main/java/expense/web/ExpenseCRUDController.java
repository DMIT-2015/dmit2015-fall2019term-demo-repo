package expense.web;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJBAccessException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import expense.entity.Expense;
import expense.service.ExpenseBean;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ExpenseCRUDController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Logger logger;
	
	@Inject
	private ExpenseBean expenseBean;
	
	private List<expense.entity.Expense> expenses;					// +getter
	
	/** The current Expense to create, edit, update, or delete */
	@Getter @Setter 
	private Expense expenseDetail = new Expense();	// +getter +setter	
	
	@Getter @Setter 
	private boolean editMode = false;
	
	@Getter @Setter 
	private Long editId = null;
	
	@PostConstruct
	void init() {
		try {
			expenses = expenseBean.findAllByLoginUser();
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			
			String systemMessage = String.format("Unauthorized access from IP %s and user %s", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(systemMessage);
		} catch(Exception e) {
			Messages.addGlobalInfo("Retrieve list of {0} was not successful.", Expense.class.getSimpleName());
		}
	}
	
	public String create() {
		String outcome = null;
		try {
			expenseBean.add(expenseDetail);
			expenseDetail = new Expense();
			Messages.addFlashGlobalInfo("Create was succesful");
			outcome = "list?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			
			String systemMessage = String.format("Unauthorized access from IP %s and user %s", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(systemMessage);
		} catch(Exception e) {
			Messages.addGlobalInfo("Create new {0} was not successful.", Expense.class.getSimpleName());
		}
		
		return outcome;
	}
	
	public String update() {
		String outcome = null;
		try {
			expenseBean.update(expenseDetail);
			expenseDetail = new Expense();
			editMode = false;
			editId = null;
			Messages.addFlashGlobalInfo("Update was succesful");
			outcome = "list?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			
			String systemMessage = String.format("Unauthorized access from IP %s and user %s", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(systemMessage);
		} catch(Exception e) {
			Messages.addGlobalInfo("Update existing {0} was not successful.", Expense.class.getSimpleName());
		}
		
		return outcome;
	}
	
	public void delete(Expense existingExpense) {
		try {
			expenseBean.remove(existingExpense);
			expenses.remove(existingExpense);
			Messages.addGlobalInfo("Delete was successful");
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			
			String systemMessage = String.format("Unauthorized access from IP %s and user %s", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(systemMessage);	
		} catch(Exception e) {
			Messages.addGlobalInfo("Delete existing {0} was not successful.", Expense.class.getSimpleName());
		}
	}

	public String delete() {
		String nextUrl = null;
		try {
			expenseBean.remove(expenseDetail);
			expenses.remove(expenseDetail);
			expenseDetail = null;
			editId = null;
			Messages.addFlashGlobalInfo("Delete was successful");			
			nextUrl = "list?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalInfo(e.getMessage());
			
			String systemMessage = String.format("Unauthorized access from IP %s and user %s", Faces.getRemoteAddr(), Faces.getRemoteUser());			
			logger.warning(systemMessage);	
		} catch(Exception e) {
			Messages.addGlobalInfo("Delete existing {0} was not successful.", Expense.class.getSimpleName());
		}
		return nextUrl;
	}
	
	
	public void edit() {
		if (!Faces.isPostback() && !Faces.isValidationFailed() ) {
			if (editId != null) {
				try {
					expenseDetail = expenseBean.findById(editId);
					if (expenseDetail != null) {
						editMode = true;
					} else {
						Messages.addFlashGlobalError("{0} is not a valid id value", editId);
						Faces.navigate("list?faces-redirect=true");						
					}
				} catch (Exception e) {
					Messages.addGlobalError("Query unsucessful");
					logger.info(e.getMessage());	
				}	
			} else {
				Faces.navigate("list?faces-redirect=true");	
			}
		} 
	}
	
	public String cancel() {
		expenseDetail = null;
		editMode = false;
		return "list?faces-redirect=true";
	}
	
	
	public List<Expense> getExpenses() {
		return expenses;
	}	
}