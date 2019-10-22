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
public class ViewScheduledPhotoEnforcementZoneBySpeedLimitAndRoadNameController implements Serializable {
	private static final long serialVersionUID = 1L;

	private ScheduledPhotoEnforcementZoneDetailManager zoneManager;
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> filteredZones;

	@Getter @Setter
	private Integer selectedSpeedLimit;
	
	@Getter
	private List<Integer> distinctSpeedLimits;
	
	@Getter @Setter
	private String selectedRoadName;
	
	@Getter
	private List<String> distinctRoadNames;
	
	@PostConstruct
	void init() {
		try {
			zoneManager = new ScheduledPhotoEnforcementZoneDetailManager();
			distinctSpeedLimits = zoneManager.getDistinctSpeedLimits();
			distinctRoadNames = zoneManager.getDistinctRoadNames();
		} catch (IOException e) {
			Messages.addGlobalFatal("{0}: error loading data from CSV file", ViewScheduledPhotoEnforcementZoneBySpeedLimitController.class.getSimpleName());
		}
	}
	
	public void filterZones() {
		if (selectedSpeedLimit != null && selectedRoadName != null) {
			filteredZones = zoneManager.getZonesBySpeedLimitAndRoadName(selectedSpeedLimit, selectedRoadName);
			Messages.addGlobalInfo("Zones with a speed limit of {0} km/h and at {1}", selectedSpeedLimit, selectedRoadName);
		} else if (selectedSpeedLimit != null && selectedRoadName == null) {
			filteredZones = zoneManager.getZonesBySpeedLimit(selectedSpeedLimit);
			Messages.addGlobalInfo("Zones with a speed limit of {0} km/h", selectedSpeedLimit);
		} else if (selectedSpeedLimit == null && selectedRoadName != null) {
			filteredZones = zoneManager.getZonesByRoadName(selectedRoadName);
			Messages.addGlobalInfo("Zones for {0}", selectedRoadName);
		} else {
			filteredZones = null;
		}
	}
	
}
