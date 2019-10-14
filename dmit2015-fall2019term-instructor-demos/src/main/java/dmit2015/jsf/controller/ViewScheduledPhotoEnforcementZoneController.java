package dmit2015.jsf.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import javax.inject.Named;

import org.omnifaces.util.Messages;

import dmit2015.csv.ScheduledPhotoEnforcementZoneDetail;
import dmit2015.csv.ScheduledPhotoEnforcementZoneDetailManager;
import lombok.Getter;

@Named
@ApplicationScoped
public class ViewScheduledPhotoEnforcementZoneController {

	private ScheduledPhotoEnforcementZoneDetailManager zoneManager;
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> zones;
	
	@PostConstruct
	void init() {
		try {
			zoneManager = new ScheduledPhotoEnforcementZoneDetailManager();
			zones = zoneManager.getZones();
		} catch (IOException e) {
			Messages.addGlobalFatal("{0}: error loading data from CSV file", ViewScheduledPhotoEnforcementZoneController.class.getSimpleName());
		}
	}
}
