package expense.service;

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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.SecurityContext;

import expense.entity.Expense;
import security.entity.LoginUser;
import security.service.LoginUserBean;

@Stateless
@PermitAll
public class ExpenseBean {
	
	@Inject
	private LoginUserBean loginUserBean;
	
	@Inject
	private SecurityContext securityContext;
	
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
		
	}

	@PersistenceContext
	private EntityManager entityManager;
	
	@RolesAllowed(value = {"**"})
	public void add(Expense newExpense) {
		String username = securityContext.getCallerPrincipal().getName();
		LoginUser loginUser = loginUserBean.findOneByUsername(username);
		newExpense.setUser(loginUser);
		
		entityManager.persist(newExpense);
		
		// send a notification in 30000ms (30 seconds)
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setInfo(newExpense);
		timerService.createSingleActionTimer(30000, timerConfig);
	}
	
	@RolesAllowed("**")
	public void update(Expense existingExpense) {
		entityManager.merge(existingExpense);
		entityManager.flush();	
	}
	
	@RolesAllowed("**")
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
			if (currentExpense != null && currentExpense.getId().equals(existingExpense.getId())) {
				currentTimer.cancel();
				break;
			}
		}
		
	}
	
	@RolesAllowed("**")
	public void remove(Long id) {
		Expense existingCategoy = findById(id);
		remove(existingCategoy);
	}
	
	@RolesAllowed("**")
	public Expense findById(Long id) {
		return entityManager.find(Expense.class, id);
	}
	
	@RolesAllowed("**")
	public List<Expense> findAll() {
		String username = securityContext.getCallerPrincipal().getName();

		return entityManager.createQuery(
				"SELECT e FROM Expense e WHERE e.user.username = :usernameValue ORDER BY e.date DESC"
			, Expense.class)
			.setParameter("usernameValue", username)	
			.getResultList();
	}
	
}
