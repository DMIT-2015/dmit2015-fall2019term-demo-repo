package northwind.repository;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractNorthwindJpaRepository<T> {

	@PersistenceContext(unitName="northwind-jpa-pu")
	private EntityManager entityManager;
	
	private final Class<T> entityClass;

    public AbstractNorthwindJpaRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    protected EntityManager getEntityManager() {
    	return entityManager;
    }

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