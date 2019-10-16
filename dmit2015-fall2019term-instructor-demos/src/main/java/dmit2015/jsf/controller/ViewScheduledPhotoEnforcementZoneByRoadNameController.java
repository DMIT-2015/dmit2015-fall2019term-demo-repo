package dmit2015.jsf.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import dmit2015.csv.ScheduledPhotoEnforcementZoneDetail;
import dmit2015.csv.ScheduledPhotoEnforcementZoneDetailManager;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class ViewScheduledPhotoEnforcementZoneByRoadNameController implements Serializable {
	private static final long serialVersionUID = 1L;

	private ScheduledPhotoEnforcementZoneDetailManager zoneManager;
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> zones;

	@Getter @Setter
	private String selectedRoadName;
	
	@Getter
	private List<String> distinctRoadNames;
	
	@PostConstruct
	void init() {
		try {
			zoneManager = new ScheduledPhotoEnforcementZoneDetailManager();
			distinctRoadNames = zoneManager.getDistinctRoadNames();
		} catch (IOException e) {
			Messages.addGlobalFatal("{0}: error loading data from CSV file", ViewScheduledPhotoEnforcementZoneByRoadNameController.class.getSimpleName());
		}
	}
	
	public void changeRoadName() {
		if (selectedRoadName != null) {
			zones = zoneManager.getZonesByRoadName(selectedRoadName);
			Messages.addGlobalInfo("Zones for {0}", selectedRoadName);
		} else {
			zones = null;
		}
	}
}
