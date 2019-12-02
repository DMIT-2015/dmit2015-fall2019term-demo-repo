package security.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import security.entity.*;

@Singleton
//@Interceptors({LoginUserSecurityInterceptor.class})
public class LoginUserBean {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private Pbkdf2PasswordHash passwordHash;
	
	@EJB
	private LoginGroupBean loginGroupBean;
	
	@Lock(LockType.WRITE)
	public void add(LoginUser newLoginUser, String[] groupNames) {

		String hashedPassword = passwordHash.generate(newLoginUser.getPlainTextPassword().toCharArray());        
        newLoginUser.setPassword(hashedPassword);

        if (groupNames != null && groupNames.length > 0 ) {
            for(String singleGroupName : groupNames ) {
            	LoginGroup userGroup = loginGroupBean.findOneByGroupName(singleGroupName);
            	newLoginUser.getGroups().add(userGroup);
            }
        }

		entityManager.persist(newLoginUser);
	}
	
	public void update(LoginUser existingLoginUser, String[] groupNames) {
        if (groupNames != null && groupNames.length > 0 ) {
        	existingLoginUser.getGroups().clear();
            for(String singleGroupName : groupNames ) {
            	LoginGroup userGroup = loginGroupBean.findOneByGroupName(singleGroupName);
            	existingLoginUser.getGroups().add(userGroup);
            }
        }

        entityManager.merge(existingLoginUser);
		entityManager.flush();
	}

	public void delete(LoginUser existingLoginUser) {
		if (!entityManager.contains(existingLoginUser)) {
			existingLoginUser = entityManager.merge(existingLoginUser);
		}
		entityManager.remove(existingLoginUser);
	}

	public LoginUser findOneById(Long userId) {
		return entityManager.find(LoginUser.class, userId);
	}
	
	public LoginUser findOneByUsername(String username) {
		LoginUser querySingleResult = null;
		
		try {
			querySingleResult = entityManager.createQuery(
				"SELECT u FROM LoginUser u WHERE u.username = :usernameValue "
				, LoginUser.class)
				.setParameter("usernameValue", username)
				.getSingleResult();
		} catch(NoResultException e) {
			querySingleResult = null;
		}
		
		return querySingleResult;
	}
	
	@Lock(LockType.READ)	// grant multi-threaded access to this method
	public List<LoginUser> list() {
		return entityManager.createQuery("SELECT u FROM LoginUser u ORDER BY u.username", LoginUser.class).getResultList();
	}

	public void changePassword(String username, String currentPlainTextPassword, String newPlainTextPassword) throws Exception {
		char[] currentPassword = currentPlainTextPassword.toCharArray();
		
		LoginUser existingLoginUser = findOneByUsername(username);
		
		// verify currentPlainTextPassword is valid
		if (passwordHash.verify(currentPassword, existingLoginUser.getPassword())) {
			String newHashedPassword = passwordHash.generate(newPlainTextPassword.toCharArray());
			existingLoginUser.setPassword(newHashedPassword);
			entityManager.merge(existingLoginUser);
		} else {
			throw new Exception("Current password is incorrect.");
		}
	}
}
