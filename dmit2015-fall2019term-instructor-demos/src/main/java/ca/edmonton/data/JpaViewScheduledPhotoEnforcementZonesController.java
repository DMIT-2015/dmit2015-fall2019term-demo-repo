package ca.edmonton.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;

@Named
@ApplicationScoped
public class JpaViewScheduledPhotoEnforcementZonesController {
	
	@Inject
	private ScheduledPhotoEnforcementZoneDetailBean zoneBean;
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> zones;
	
	@Getter
	private List<String> distinctRoadNames;
	
	@PostConstruct
	void init() {
		zones = zoneBean.findAllOrderByRoadNameDescending();
		distinctRoadNames = zoneBean.findDistinctRoadNames();
	}
}
