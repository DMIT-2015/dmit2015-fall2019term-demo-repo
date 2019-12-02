package security.service;

import java.util.List;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import security.entity.*;

@Singleton
//@Interceptors({LoginGroupSecurityInterceptor.class})
public class LoginGroupBean {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Lock(LockType.WRITE)	// only a single thread can access this method 
	public void add(String groupname) {
		if (findOneByGroupName(groupname) != null) {
			throw new RuntimeException("The group name " + groupname + " already exists");
		}
		LoginGroup newLoginGroup = new LoginGroup();
		newLoginGroup.setGroupname(groupname);
		entityManager.persist(newLoginGroup);
	}

	public void update(LoginGroup existingLoginGroup) {
		entityManager.merge(existingLoginGroup);
		entityManager.flush();
	}

	public void delete(LoginGroup existingLoginGroup) {
		if (!entityManager.contains(existingLoginGroup)) {
			existingLoginGroup = entityManager.merge(existingLoginGroup);
		}
		entityManager.remove(existingLoginGroup);
	}

	public LoginGroup findOneById(Long groupId) {
		return entityManager.find(LoginGroup.class, groupId);
	}
	
	public LoginGroup findOneByGroupName(String groupname) {
		LoginGroup querySingleResult = null;
		
		try {
			querySingleResult = entityManager.createQuery(
				"SELECT g FROM LoginGroup g WHERE g.groupname = :groupnameValue "
				, LoginGroup.class)
				.setParameter("groupnameValue", groupname)
				.getSingleResult();
		} catch(NoResultException e) {
			querySingleResult = null;
		}
		
		return querySingleResult;
	}
	
	@Lock(LockType.READ)	// grant multi-threaded access to this method
	public List<LoginGroup> list() {
		return entityManager.createQuery("SELECT g FROM LoginGroup g ORDER BY g.groupname", LoginGroup.class).getResultList();
	}

}
