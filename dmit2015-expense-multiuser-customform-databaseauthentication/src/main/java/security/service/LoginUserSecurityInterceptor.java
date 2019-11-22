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

@DeclareRoles({"DEVELOPER","USER","ADMIN"})
public class LoginUserSecurityInterceptor {
	
	@Inject
	private Logger logger;
	
	@Resource
	private SessionContext sessionContext;
	
	@AroundInvoke
	public Object verifyAccess(InvocationContext context) throws Exception {
		String methodName = context.getMethod().getName();
		logger.info("Intercepting invoke to method: " + methodName);
		
		if (methodName.matches("^delete.*$") || methodName.matches("^list.*$")) {
			if (!sessionContext.isCallerInRole(SecurityRole.DEVELOPER.toString())) {
				String systemMessage = String.format("Unauthorized access to method: %s from IP %s and user %s", methodName, Faces.getRemoteAddr(), Faces.getRemoteUser());			
				logger.warning(systemMessage);
				
				String message = String.format("Access denied! You do not have permission to execute this method");
				logger.warning(message);
				throw new EJBAccessException(message);
			}			
		} else if (methodName.matches("^update.*$") || methodName.matches("changePassword")) {
			if (!sessionContext.isCallerInRole(SecurityRole.DEVELOPER.toString()) && !sessionContext.isCallerInRole(SecurityRole.ADMIN.toString()) && !sessionContext.isCallerInRole(SecurityRole.USER.toString())) {
				String systemMessage = String.format("Unauthorized access to method: %s from IP %s and user %s", methodName, Faces.getRemoteAddr(), Faces.getRemoteUser());			
				logger.warning(systemMessage);

				String message = String.format("Access denied! You do not have permission to execute this method");
				logger.warning(message);
				throw new EJBAccessException(message);
			}			
		} 
		
		Object result = context.proceed();
		logger.info("Return from invoking method: " + methodName);
		return result;
	}
}
