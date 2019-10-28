package ca.edmonton.data.batch;

import javax.batch.api.listener.AbstractStepListener;
import javax.inject.Named;

@Named
public class ScheduledPhotoEnforcementZoneDetailBatchItemStepListener extends AbstractStepListener {

	@Override
	public void beforeStep() throws Exception {
		System.out.println("Batch Processing: beforeStep() is executing");
	}

	@Override
	public void afterStep() throws Exception {
		System.out.println("Batch Processing: afterStep() is executing");
	}

	
}
