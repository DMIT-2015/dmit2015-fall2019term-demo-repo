package ca.edmonton.data;

import javax.annotation.PostConstruct;
import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class SchedulePhotoEnforcementZoneDetailRegistry {

	@PostConstruct
	public void init() {
		try {
			JobOperator jobOperator = BatchRuntime.getJobOperator();
			long jobId = jobOperator.start("csvToJsonJob", null);
			System.out.println("Job submitted: " + jobId);
		} catch(JobStartException | JobSecurityException ex) {
			System.out.println("Error submitting job! " + ex.getMessage());
		}
	}
	
}
