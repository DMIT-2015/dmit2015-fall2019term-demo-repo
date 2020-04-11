package expense.service;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import expense.entity.Expense;

@Stateless
//@Interceptors({ExpenseSecurityInterceptor.class})
public class ExpenseBean {
	
	@Inject
	private Logger logger;
	
	@Resource
	TimerService timerService;
	
	@Timeout
	public void sendNotification(Timer timer) {
		Expense currentExpense = (Expense) timer.getInfo();
		System.out.println("New Transaction Notification");
		System.out.println("Description: " + currentExpense.getDescription());
		System.out.println("Amount: " + currentExpense.getAmount());
		System.out.println("Date: " + currentExpense.getDate().toString());
		
//		InputStream inputStream = getClass().getResourceAsStream("/application.properties");
//		if (inputStream == null)
//			System.out.println("No input file");
//		else
//			System.out.println("Found input file");	
	}

	@PersistenceContext
	private EntityManager entityManager;
	
//	@RolesAllowed({"USER","ADMIN"})
	@PermitAll
	public void add(Expense newExpense) {
		entityManager.persist(newExpense);
		
		// send a notification in 30000ms (30 seconds)
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setInfo(newExpense);
		timerService.createSingleActionTimer(30000, timerConfig);
	}
	
	@RolesAllowed({"USER","ADMIN"})
	public void update(Expense existingExpense) {
		entityManager.merge(existingExpense);
		entityManager.flush();	
	}
	
//	@RolesAllowed({"ADMIN"})
	@PermitAll
	public void remove(Expense existingExpense) {
		if (!entityManager.contains(existingExpense)) {
			existingExpense = entityManager.merge(existingExpense);			
		}
		entityManager.remove(existingExpense);
		entityManager.flush();
		
		// remove timer associated with existingExpense
		Collection<Timer> activeTimers = timerService.getAllTimers();
		for(Timer currentTimer : activeTimers) {
			Expense currentExpense = (Expense) currentTimer.getInfo();
			if (currentExpense.getId().equals(existingExpense.getId())) {
				currentTimer.cancel();
				break;
			}
		}
		
	}
	
	public void remove(Long id) {
		Expense existingCategoy = findById(id);
		remove(existingCategoy);
	}
	
	@PermitAll
	public Expense findById(Long id) {
		return entityManager.find(Expense.class, id);
	}
	
	@PermitAll
	public List<Expense> findAll() {
		return entityManager.createQuery(
				"SELECT e FROM Expense e ORDER BY e.date DESC"
			, Expense.class)
			.getResultList();
	}
	
}
