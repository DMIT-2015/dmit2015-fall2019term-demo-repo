package security.web;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;
import javax.servlet.http.HttpServletRequest;

@Named
@ApplicationScoped
public class SecurityController {
	
	@Inject
	private SecurityContext securityContext;

	public boolean hasAccessToWebResource(String resource) {
		return securityContext.hasAccessToWebResource(resource, "GET");
	}
	
	public static boolean isInAnyRole(HttpServletRequest request, String... roles) {
		for (String singleRole : roles) {
			if (request.isUserInRole(singleRole)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isInAllRole(HttpServletRequest request, String... roles) {
		for (String singleRole : roles) {
			if (!request.isUserInRole(singleRole)) {
				return false;
			}
		}
		return true;
	}
}
