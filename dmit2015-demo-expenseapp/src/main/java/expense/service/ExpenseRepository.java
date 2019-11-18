package expense.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import expense.entity.Expense;

public class ExpenseRepository extends AbstractJpaRepository<Expense> {
	
	public ExpenseRepository() {
		super(Expense.class);
	}
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	

}
