package ca.edmonton.data.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
@Startup
public class SchedulePhotoEnforcementZoneDetailStartupBean {

	@Schedule(second = "0", minute = "*", hour = "*",
			persistent = false)
	public void displayMemoryReport(Timer timer) {
//		System.out.println("It's time to generate a report");
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Total memory: " + runtime.totalMemory());
		System.out.println("Maximum memory: " + runtime.maxMemory());
		System.out.println("Free memory: " + runtime.freeMemory());
	}
	
	@Resource
	private TimerService timerService;
	
	@Timeout
	public void timeout(Timer timer) {
		try {
			JobOperator jobOperator = BatchRuntime.getJobOperator();
			long jobId = jobOperator.start("csvToJsonJob", null);
			System.out.println("Job submitted: " + jobId);
		} catch(JobStartException | JobSecurityException ex) {
			System.out.println("Error submitting job! " + ex.getMessage());
		}
	}
	
	@PostConstruct
	public void init() {
		// Single event timers
		timerService.createSingleActionTimer(5000, 
			new TimerConfig());
		
		// Interval event timers
//		timerService.createIntervalTimer(10000, 60000, 
//			new TimerConfig());
		
		// Calendar event timers
//		ScheduleExpression scheduleExpression = new ScheduleExpression();
//		scheduleExpression.year(2019);
//		scheduleExpression.month(11);
//		scheduleExpression.dayOfMonth(4);
//		scheduleExpression.hour(15);
//		scheduleExpression.minute(45);
//		timerService.createCalendarTimer(
//				scheduleExpression,
//				new TimerConfig());
		
	}
	
}
