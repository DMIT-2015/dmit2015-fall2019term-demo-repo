package expense.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import expense.entity.Expense;

@Stateless
public class ExpenseBean {
	
	@Resource
	TimerService timerService;
	
	@Timeout
	public void sendNotification(Timer timer) {
		Expense currentExpense = (Expense) timer.getInfo();
		System.out.println("New Transaction Notification");
		System.out.println("Description: " + currentExpense.getDescription());
		System.out.println("Amount: " + currentExpense.getAmount());
		System.out.println("Date: " + currentExpense.getDate().toString());
	}

	@PersistenceContext
	private EntityManager entityManager;
	
	public void add(Expense newExpense) {
		entityManager.persist(newExpense);
		
		// send a notification in 5000ms
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setInfo(newExpense);
		timerService.createSingleActionTimer(5000, timerConfig);
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
