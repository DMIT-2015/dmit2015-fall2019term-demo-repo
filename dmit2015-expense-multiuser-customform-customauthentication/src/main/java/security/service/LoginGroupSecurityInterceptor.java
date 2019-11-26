package security.service;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

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
			boolean isInDeveloperRole = sessionContext.isCallerInRole(SecurityRole.DEVELOPER.toString());
			boolean isInAdminRole = sessionContext.isCallerInRole(SecurityRole.ADMIN.toString());
			if (!isInDeveloperRole && !isInAdminRole) {
				String username = sessionContext.getCallerPrincipal().getName();
				String systemMessage = String.format("Unauthorized access to method \"%s\" from username \"%s\".", methodName, username);			
				logger.warning(systemMessage);
				String userMessage = String.format("Access denied! User \"%s\" do not have permission to execute this method", username);
				throw new EJBAccessException(userMessage);
			}			
		} 
		
		Object result = context.proceed();
		logger.info("Return from invoking method: " + methodName);
		return result;
	}
}
