package expense.resource;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import expense.entity.Expense;
import expense.service.ExpenseBean;

/**
 *  URI						Http Method		Request Body						Description
 * 	----------------------	-----------		--------------------------------	------------------------------------------	
 *	/expenses				POST			{"description":"value",				Create a new Expense entity
 *											"amount":1.23,
 *											"date":"yyyy-MM-dd"}				
 * 	/expenses				GET													Return a list all Expense entity
 * 	/expenses/{id}			GET													Return one Expense entity by id 
 * 	/expenses/{id}			PUT				{"description":"value",				Update the Expense enttity by id
 * 											"amount":1.23,
 * 											"date":"yyyy-MM-dd"}		
 * 	/expenses/{id}			DELETE												Delete the Expense entity by id
 * 
 * Create three new Expense
curl -i -k -X POST https://localhost:8443/webapi/expenses \
 -d '{"description":"April 1 item","amount": 1.11, "date":"2020-04-01"}' \
 -H 'Content-Type:application/json' \
 -u user2015:Password2015
 
 
 
 * List all expenses
curl -i -k -X GET https://localhost:8443/webapi/expenses 
  
 * @author samcw
 *
 */

@Path("expenses")
@Consumes(MediaType.APPLICATION_JSON)	// All methods this class accept only JSON format data 
@Produces(MediaType.APPLICATION_JSON)	// All methods returns data that has been converted to JSON format
public class ExpenseResoure {

	@Inject
	private ExpenseBean expenseBean;
	
	@POST			// This method only accepts HTTP POST requests.
	public Response add(@Valid Expense newExpense, @Context UriInfo uriInfo) {
				
		try {
			expenseBean.add(newExpense);
			URI location = uriInfo.getAbsolutePathBuilder()
				.path(newExpense.getId().toString())
				.build();		
			return Response
					.created(location)
					.build();
		} catch (Exception ex) {
			return Response.
					serverError()
					.entity(ex.getMessage())
					.build();
		}
		
	}
	
	
	@GET	// This method only accepts HTTP GET requests.
	public Response findAll() {
		try {
			List<Expense> resultList = expenseBean.findAll();
			return Response.ok(resultList).build();			
		} catch (Exception ex) {
			return Response
					.serverError()
					.entity(ex.getMessage())
					.build();
		}
	}
	
	@GET 			// This method only accepts HTTP GET requests.
	@Path("{id}")	// This method accepts a path parameter and gives it a name of id
	public Response find(@PathParam("id") Long id) {
		// Find the LoginGroup instance with primary key of id 
		Expense foundEntity = expenseBean.findById(id);
		
		if (foundEntity == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		return Response.ok(foundEntity).build();
	}
	
	@PUT 			// This method only accepts HTTP PUT requests.
	@Path("{id}")	// This method accepts a path parameter and gives it a name of id
	public Response update(@PathParam("id") Long id, @Valid Expense exisitingExpense) {
		Expense foundEntity = expenseBean.findById(id);
		
		if (foundEntity == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		foundEntity.setDescription(exisitingExpense.getDescription());
		foundEntity.setAmount(exisitingExpense.getAmount());
		foundEntity.setDate(exisitingExpense.getDate());
		
		try {
			expenseBean.update(foundEntity);
		} catch (Exception ex) {
			return Response.
					serverError()
					.entity(ex.getMessage())
					.build();
		}

		return Response.noContent().build();
	}

	@DELETE 		// This method only accepts HTTP DELETE requests.
	@Path("{id}")	// This method accepts a path parameter and gives it a name of id
	public Response delete(@PathParam("id") Long id) {
		Expense foundEntity = expenseBean.findById(id);
		
		if (foundEntity == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
		try {
			expenseBean.remove(foundEntity);
		} catch (Exception ex) {				
			return Response						
					.serverError()
					.encoding(ex.getMessage())
					.build();
		}
		
		return Response.noContent().build();	
	}
}
