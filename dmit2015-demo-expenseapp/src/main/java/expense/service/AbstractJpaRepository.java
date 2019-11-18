package expense.service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractJpaRepository<T> {

	private final Class<T> entityClass;

    public AbstractJpaRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(final T entity) {
        getEntityManager().persist(entity);
    }

    public T edit(T entity) {
        T t = getEntityManager().merge(entity);
        getEntityManager().flush();
        return t;
    }

    public void remove(T entity) {
    	if (!getEntityManager().contains(entity)) {
    		entity = getEntityManager().merge(entity);
    	}
    	getEntityManager().remove(entity);
        getEntityManager().flush();
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        final CriteriaQuery<T> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> entity = criteriaQuery.from(entityClass);
        criteriaQuery.select(entity);
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public List<T> findRange(int[] range) {
        final CriteriaQuery<T> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return getEntityManager().createQuery(criteriaQuery)
        		.setMaxResults(range[1] - range[0])
        		.setFirstResult(range[0])
        		.getResultList();
    }
    
    public int count() {
    	final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
    	final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
    	criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(entityClass)));
    	return ((Long) getEntityManager().createQuery(criteriaQuery).getSingleResult()).intValue();
    }

    public void deleteAll() {
        final CriteriaDelete<T> criteriaDelete = getEntityManager().getCriteriaBuilder().createCriteriaDelete(entityClass);
        criteriaDelete.from(entityClass);
        getEntityManager().createQuery(criteriaDelete).executeUpdate();
    }
    
}