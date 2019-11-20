package expense.service;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import expense.entity.Expense;

@Stateless
public class ExpenseBean {
	
	@Inject
	private Logger logger;
	
//	@Resource(name = "java:jboss:/mail/yahoo")
//	private Session session;
		
	@Resource
	TimerService timerService;
	
	@Timeout
	public void sendNotification(Timer timer) {
		Expense currentExpense = (Expense) timer.getInfo();
		System.out.println("New Transaction Notification");
		System.out.println("Description: " + currentExpense.getDescription());
		System.out.println("Amount: " + currentExpense.getAmount());
		System.out.println("Date: " + currentExpense.getDate().toString());
		
//		try {
//			Message message = new MimeMessage(session);
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("swu@nait.ca"));
//			message.setSubject("New Expense Notification");
//			message.setText(currentExpense.toString());
//			Transport.send(message);
//		} catch(MessagingException e) {
//			logger.warning("Cannot send mail with exception: " + e.getMessage());
//		}
	}

	@PersistenceContext
	private EntityManager entityManager;
	
	public void add(Expense newExpense) {
		entityManager.persist(newExpense);
		
		// send a notification in 30000ms (30 seconds)
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setInfo(newExpense);
		timerService.createSingleActionTimer(30000, timerConfig);
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
