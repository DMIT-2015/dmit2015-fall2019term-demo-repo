package dmit2015.jsf.controller;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import dmit2015.csv.ScheduledPhotoEnforcementZoneDetail;
import lombok.Getter;

@Named
@ApplicationScoped
public class CDIViewScheduledPhotoEnforcementZonesController {
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> zones;
	
	@Getter
	private List<String> distinctRoadNames;
	
	@PostConstruct
	void init() {
		try {
			CsvMapper mapper = new CsvMapper();
			CsvSchema schema = CsvSchema.emptySchema().withHeader();
			MappingIterator<ScheduledPhotoEnforcementZoneDetail> iter = mapper
					.readerFor(ScheduledPhotoEnforcementZoneDetail.class)
					.with(schema)
					.readValues(getClass().getResourceAsStream("/Scheduled_Photo_Enforcement_Zone_Details.csv"));
			zones = iter.readAll();
			Comparator<ScheduledPhotoEnforcementZoneDetail> roadNameComparator = (lhs, rhs) -> lhs.getRoadName().compareToIgnoreCase(rhs.getRoadName());
			zones = zones.stream()
					.sorted(roadNameComparator.reversed())
					.collect(Collectors.toList());
			
			Comparator<String> roadNameComparater = (lhs, rhs) -> lhs.compareToIgnoreCase(rhs);
			distinctRoadNames = zones.stream()
					.map(ScheduledPhotoEnforcementZoneDetail::getRoadName)
					.distinct()
					.filter(instance -> !instance.isBlank())
					.sorted(roadNameComparater.reversed())
					.collect(Collectors.toList());		

		} catch (IOException e) {
			Messages.addGlobalFatal("{0}: error loading data from CSV file", 
					CDIViewScheduledPhotoEnforcementZonesController.class.getSimpleName());
		}
	}
}
