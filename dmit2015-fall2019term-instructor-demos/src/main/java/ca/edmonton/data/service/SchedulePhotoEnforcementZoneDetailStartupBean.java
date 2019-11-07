package ca.edmonton.data.service;

import java.io.File;
import java.net.URL;

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

import org.apache.commons.io.FileUtils;
import org.omnifaces.util.Faces;

@Singleton
@Startup
public class SchedulePhotoEnforcementZoneDetailStartupBean {

	@Schedule(dayOfWeek = "Thu", hour = "16", minute = "03" )
	public void downloadNewCSVFile() {
		System.out.println("Its time to download a new CSV file");
		String sourePath = "https://data.edmonton.ca/api/views/4cqz-cd52/rows.csv?accessType=DOWNLOAD";
//		String destinationPath = "/home/user2015/git/dmit2015-exercises/dmit2015-fall2019term-instructor-demos/src/main/webapp/public/uploads/photo.csv";
		String realFilePath = Faces.getRealPath("/public/uploads");
		String destinationPath = realFilePath + "/PhotoData.csv";
		try {
			URL sourceURL = new URL(sourePath);
			File destinationFile = new File(destinationPath);
			FileUtils.copyURLToFile(sourceURL, destinationFile);
			System.out.println("Filedown complete");
		} catch(Exception ex) {
			System.out.println("Error downloading CSV file");
		}
	}
	
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
		System.out.println("Timer has executed");
	}
	
	@PostConstruct
	public void init() {
		
		try {
			JobOperator jobOperator = BatchRuntime.getJobOperator();
			long jobId = jobOperator.start("csvToJsonJob", null);
			System.out.println("Job submitted: " + jobId);
		} catch(JobStartException | JobSecurityException ex) {
			System.out.println("Error submitting job! " + ex.getMessage());
		}

		
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
