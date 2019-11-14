package expense.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import expense.entity.Expense;

@Stateless
public class ExpenseBean {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void add(Expense newExpense) {
		entityManager.persist(newExpense);
	}
	
	public void update(Expense existingExpense) {
		entityManager.merge(existingExpense);
		entityManager.flush();	
	}
	
	public void remove(Expense existingExpense) {
		if (!entityManager.contains(existingExpense)) {
			existingExpense = entityManager.merge(existingExpense);			
		}
		entityManager.remove(existingExpense);
		entityManager.flush();
	}
	
	public void remove(Long id) {
		Expense existingCategoy = findById(id);
		remove(existingCategoy);
	}
	
	public Expense findById(Long id) {
		return entityManager.find(Expense.class, id);
	}
	
	public List<Expense> findAll() {
		return entityManager.createQuery(
				"SELECT e FROM Expense e ORDER BY e.date DESC"
			, Expense.class)
			.getResultList();
	}
	
}
