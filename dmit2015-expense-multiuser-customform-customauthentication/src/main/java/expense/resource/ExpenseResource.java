package expense.resource;

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
import javax.ws.rs.core.MediaType;

import expense.entity.Expense;
import expense.service.ExpenseBean;

@Path("expenses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExpenseResource {

	@Inject
	private ExpenseBean expenseBean;
	
	@GET
	public List<Expense> findAll() {
		return expenseBean.findAll();
	}
	
	@GET
	@Path("{id}")
	public Expense findByUsername(@PathParam("id") Long expenseId) {
		return expenseBean.findById(expenseId);
	}
	
	@POST
	public void addExpense(Expense newExpense) {
		expenseBean.add(newExpense);
	}
	
	@PUT
	public void updateExpense(Expense existingExpense) {
		expenseBean.update(existingExpense);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteExpense(@PathParam("id") Long expenseId) {
		Expense existingExpense = expenseBean.findById(expenseId);
		if (existingExpense != null) {
			expenseBean.remove(existingExpense);			
		}
	}
}
