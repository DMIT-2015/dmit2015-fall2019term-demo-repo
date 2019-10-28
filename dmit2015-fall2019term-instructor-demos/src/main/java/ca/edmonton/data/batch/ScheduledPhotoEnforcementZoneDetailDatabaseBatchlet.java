package ca.edmonton.data.batch;

import java.util.List;

import javax.batch.api.AbstractBatchlet;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import ca.edmonton.data.entity.ScheduledPhotoEnforcementZoneDetail;

@Named
public class ScheduledPhotoEnforcementZoneDetailDatabaseBatchlet extends AbstractBatchlet {

	@PersistenceContext(unitName = "dmit2015-jpa-pu")
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public String process() throws Exception {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		MappingIterator<ScheduledPhotoEnforcementZoneDetail> iter = mapper
				.readerFor(ScheduledPhotoEnforcementZoneDetail.class)
				.with(schema)
				.readValues(getClass().getResourceAsStream("/Scheduled_Photo_Enforcement_Zone_Details.csv"));
		List<ScheduledPhotoEnforcementZoneDetail> zones = iter.readAll();
		try {
			zones.forEach(singleZone -> {
				entityManager.persist(singleZone);
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "FAILED";
		}
		
		
		return "COMPLETED";
	}

}
