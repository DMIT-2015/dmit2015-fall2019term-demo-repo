package security.data;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import security.entity.LoginUser;
import security.service.LoginUserBean;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {

	@Inject
	private LoginUserBean loginUserBean;
	
	@Inject
	private Pbkdf2PasswordHash passwordHash;
	
	@Override
	public CredentialValidationResult validate(Credential credential) {
		UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
			
		String username = login.getCaller();
		
		LoginUser existingLoginUser = loginUserBean.findOneByUsername(username);
				
		if (existingLoginUser != null && passwordHash.verify(login.getPasswordAsString().toCharArray(), existingLoginUser.getPassword())) {
			Set<String> groups = new HashSet<>();
			String[] groupArray = existingLoginUser.getGroups().split(",");
			for(String singleGroup : groupArray) {
				groups.add(singleGroup);
			}
			return new CredentialValidationResult(username, groups);
		} else {
			return CredentialValidationResult.INVALID_RESULT;
		}
	}	
	
}
