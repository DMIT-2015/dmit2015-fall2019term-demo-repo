package security.service;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import security.entity.LoginUser;

public class LoginUserSecurityInterceptor {
	
	@Inject
	private Logger logger;
	
	@Resource
	private SessionContext sessionContext;
	
	@AroundInvoke
	public Object verifyAccess(InvocationContext context) throws Exception {
		String methodName = context.getMethod().getName();
		logger.info("Intercepting invoke to method: " + methodName);
				
		// The ADMIN and DEVELOPER roles can manage any user account
		if (methodName.matches("^delete.*$") || methodName.matches("^list.*$")) {
			boolean isInDeveloperRole = sessionContext.isCallerInRole(SecurityRole.DEVELOPER.toString());
			boolean isInAdminRole = sessionContext.isCallerInRole(SecurityRole.ADMIN.toString());
			if (!isInDeveloperRole && !isInAdminRole) {
				String username = sessionContext.getCallerPrincipal().getName();
				String systemMessage = String.format("Unauthorized access to method \"%s\" from username \"%s\".", methodName, username);			
				logger.warning(systemMessage);
				String userMessage = String.format("Access denied! User \"%s\" do not have permission to execute this method", username);
				throw new EJBAccessException(userMessage);
			}			
		} else if (methodName.matches("^update.*$")) {
			boolean isInDeveloperRole = sessionContext.isCallerInRole(SecurityRole.DEVELOPER.toString());
			boolean isInAdminRole = sessionContext.isCallerInRole(SecurityRole.ADMIN.toString());
			if (!isInDeveloperRole && !isInAdminRole) {
				LoginUser existingUser = (LoginUser) context.getParameters()[0];
				String username = sessionContext.getCallerPrincipal().getName();
				if (!existingUser.getUsername().equals(username)) {
					String systemMessage = String.format("Unauthorized access to method \"%s\" from username \"%s\".", methodName, username);			
					logger.warning(systemMessage);
					String userMessage = String.format("Access denied! You do not have permission to update information on other users", username);
					throw new EJBAccessException(userMessage);					
				}
			}			
		} else if (methodName.matches("changePassword")) {
			String username = sessionContext.getCallerPrincipal().getName();
			String updateUsername = (String) context.getParameters()[0];
			if (!username.equals(updateUsername)) {
				String systemMessage = String.format("Unauthorized access to method \"%s\" from username \"%s\".", methodName, username);			
				logger.warning(systemMessage);
				String userMessage = String.format("Access denied! You do not have permission to change password for another user", username);
				throw new EJBAccessException(userMessage);			
			}
		}
		
		Object result = context.proceed();
		logger.info("Return from invoking method: " + methodName);
		return result;
	}
}
