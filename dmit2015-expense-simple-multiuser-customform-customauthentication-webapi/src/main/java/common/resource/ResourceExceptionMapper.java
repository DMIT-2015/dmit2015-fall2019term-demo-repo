package common.resource;

import javax.ejb.EJBAccessException;
import javax.ejb.EJBException;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		if (exception instanceof IllegalArgumentException || exception instanceof ConstraintViolationException) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else 	if (exception instanceof EJBException){
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else 	if (exception instanceof EJBAccessException ) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
		
		return Response.serverError().build();
	}

}
