package ca.edmonton.data;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Named
public class ScheduledPhotoEnforcementZoneDetailBatchItemWriter extends AbstractItemWriter {

	@Inject
	private JobContext jobContext;
	
	@PersistenceContext(unitName = "dmit2015-jpa-pu")
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void writeItems(List<Object> items) throws Exception {
		
		String outputFile = (String) jobContext.getProperties().get("output_file");
		
		try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile,true))) {
			
			for (Object singleItem : items) {
				JsonObject jsonItem = (JsonObject) singleItem;
				System.out.println("Writer " + jsonItem.toString());
				writer.write(jsonItem.toString());
				
				ScheduledPhotoEnforcementZoneDetail zone = new ScheduledPhotoEnforcementZoneDetail();
				zone.setSiteId(jsonItem.getInt("Site ID"));
				zone.setRoadName(jsonItem.getString("Road Name"));
				zone.setLocationDescription(jsonItem.getString("Location Description"));
				zone.setDirection(jsonItem.getString("Direction"));
				zone.setFromPoint(jsonItem.getString("From Point"));
				zone.setToPoint(jsonItem.getString("To Point"));
				zone.setSpeedLimit(jsonItem.getInt("Speed Limit"));
				zone.setLatitude(jsonItem.getJsonNumber("Latitude").doubleValue());
				zone.setLongitude(jsonItem.getJsonNumber("Longitude").doubleValue());
				
				entityManager.persist(zone);
			}
			writer.flush();
		} catch(Exception e) {
			System.err.println("Error: " + e.getMessage());
			throw new Exception(e);
		}
		
	}

}
