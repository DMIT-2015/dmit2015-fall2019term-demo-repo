package ca.edmonton.data.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;

@Named
public class ScheduledPhotoEnforcementZoneDetailBatchItemProcessor implements ItemProcessor {

	/**
	 * Write out all lines
	 */
	@Override
	public JsonObject processItem(Object item) throws Exception {
		String line = (String) item;
		final String delimiter = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
		String[] values = line.split(delimiter);
		
		Integer siteId = Integer.valueOf(values[0]);
		String roadName = values[1];
		String locationDescription = values[2];
		String direction = values[3];
		String fromPoint = values[4];
		String toPoint = values[5];
		int speedLimit = Integer.parseInt(values[6]);
		double latitude = Double.parseDouble(values[7]);
		double longitude = Double.parseDouble(values[8]);
		
		JsonObject model = Json.createObjectBuilder()
				.add("Site ID", siteId)
				.add("Road Name", roadName)
				.add("Location Description", locationDescription)
				.add("Direction", direction)
				.add("From Point", fromPoint)
				.add("To Point", toPoint)
				.add("Speed Limit", speedLimit)
				.add("Latitude", latitude)
				.add("Longitude", longitude)
				.build();
		
		return model;
	}

}
