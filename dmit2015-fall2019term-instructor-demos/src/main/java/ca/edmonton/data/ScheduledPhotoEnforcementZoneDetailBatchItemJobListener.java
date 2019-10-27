package ca.edmonton.data;

import javax.batch.api.listener.AbstractJobListener;
import javax.inject.Named;

@Named
public class ScheduledPhotoEnforcementZoneDetailBatchItemJobListener extends AbstractJobListener {

	@Override
	public void beforeJob() throws Exception {
		System.out.println("Batch Processing: beforeJob() is executing");
	}

	@Override
	public void afterJob() throws Exception {
		System.out.println("Batch Processing: afterJob() is executing");
	}

	
}
