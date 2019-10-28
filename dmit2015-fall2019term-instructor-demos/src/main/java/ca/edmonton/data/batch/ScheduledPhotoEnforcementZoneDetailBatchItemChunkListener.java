package ca.edmonton.data.batch;

import javax.batch.api.chunk.listener.AbstractChunkListener;
import javax.inject.Named;

@Named
public class ScheduledPhotoEnforcementZoneDetailBatchItemChunkListener extends AbstractChunkListener {

	@Override
	public void beforeChunk() throws Exception {
		System.out.println("Batch Processing: beforeChunk() is executing");
	}

	@Override
	public void onError(Exception ex) throws Exception {
		System.out.println("Batch Processing: onError() is executing");
	}

	@Override
	public void afterChunk() throws Exception {
		System.out.println("Batch Processing: afterChunk() is executing");
	}
	
}
