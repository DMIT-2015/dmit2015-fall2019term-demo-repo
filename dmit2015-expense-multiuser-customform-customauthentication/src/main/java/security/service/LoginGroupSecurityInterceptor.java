package security.service;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.omnifaces.util.Faces;

@DeclareRoles({"DEVELOPER","MEMBER","ADMIN"})
public class LoginGroupSecurityInterceptor {

	@Inject
	private Logger logger;
	
	@Resource
	private SessionContext sessionContext;
	
	@AroundInvoke
	public Object verifyAccess(InvocationContext context) throws Exception {
		String methodName = context.getMethod().getName();
		logger.info("Intercepting invoke to method: " + methodName);
		
		if (methodName.matches("^delete.*$") || methodName.matches("^update.*$") || methodName.matches("^list.*$") || methodName.matches("^add.*$")) {
			if (!sessionContext.isCallerInRole(SecurityRole.DEVELOPER.toString()) && !sessionContext.isCallerInRole(SecurityRole.ADMIN.toString())) {
				String systemMessage = String.format("Unauthorized access to method: %s from IP %s and user %s", methodName, Faces.getRemoteAddr(), Faces.getRemoteUser());			
				logger.warning(systemMessage);
				
				String userMessage = String.format("Access denied! You do not have permission to execute this method");
				throw new EJBAccessException(userMessage);
			}
			
		} 
		
		Object result = context.proceed();
		logger.info("Return from invoking method: " + methodName);
		return result;
	}
}
