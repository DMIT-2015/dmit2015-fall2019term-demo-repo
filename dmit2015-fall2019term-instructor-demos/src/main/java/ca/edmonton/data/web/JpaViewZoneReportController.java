package ca.edmonton.data.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ca.edmonton.data.entity.ScheduledPhotoEnforcementZoneDetail;
import ca.edmonton.data.pojo.ZoneReport;
import ca.edmonton.data.service.ScheduledPhotoEnforcementZoneDetailBean;
import lombok.Getter;

@Named
@ApplicationScoped
public class JpaViewZoneReportController {
	
	@Inject
	private ScheduledPhotoEnforcementZoneDetailBean zoneBean;
	
	@Getter
	private List<ZoneReport> reports;
	
	
	@PostConstruct
	void init() {
		reports = zoneBean.findZoneReport();
	}
}
