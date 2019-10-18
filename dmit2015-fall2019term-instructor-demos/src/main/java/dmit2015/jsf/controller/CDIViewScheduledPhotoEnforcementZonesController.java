package dmit2015.jsf.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dmit2015.csv.ScheduledPhotoEnforcementZoneDetail;
import dmit2015.csv.ScheduledPhotoEnforcementZoneDetailRepository;
import lombok.Getter;

@Named
@ApplicationScoped
public class CDIViewScheduledPhotoEnforcementZonesController {
	
	@Inject
	private ScheduledPhotoEnforcementZoneDetailRepository spezdRepository;
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> zones;
	
	@Getter
	private List<String> distinctRoadNames;
	
	@PostConstruct
	void init() {
		zones = spezdRepository.getZonesOrderByRoadNameDescending();
		distinctRoadNames = spezdRepository.getDistinctRoadNames();
	}
}
