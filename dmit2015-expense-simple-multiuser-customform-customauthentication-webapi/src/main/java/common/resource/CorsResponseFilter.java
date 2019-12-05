package common.resource;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
	
		requestContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		
		requestContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");		

		requestContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		
		requestContext.getHeaders().add("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, HEAD");	    

		requestContext.getHeaders().add("Access-Control-Max-Age", "1209600");
	}

}
