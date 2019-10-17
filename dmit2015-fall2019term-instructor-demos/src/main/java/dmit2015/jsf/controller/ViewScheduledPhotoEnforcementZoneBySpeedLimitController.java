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
public class ViewScheduledPhotoEnforcementZoneBySpeedLimitController implements Serializable {
	private static final long serialVersionUID = 1L;

	private ScheduledPhotoEnforcementZoneDetailManager zoneManager;
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> filteredZones;

	@Getter @Setter
	private Integer selectedSpeedLimit;
	
	@Getter
	private List<Integer> distinctSpeedLimits;
	
	@PostConstruct
	void init() {
		try {
			zoneManager = new ScheduledPhotoEnforcementZoneDetailManager();
			distinctSpeedLimits = zoneManager.getDistinctSpeedLimits();
		} catch (IOException e) {
			Messages.addGlobalFatal("{0}: error loading data from CSV file", ViewScheduledPhotoEnforcementZoneBySpeedLimitController.class.getSimpleName());
		}
	}
	
	public void changeSpeedLimit() {
		if (selectedSpeedLimit != null) {
			filteredZones = zoneManager.getZonesBySpeedLimit(selectedSpeedLimit);
			Messages.addGlobalInfo("Zones with a speed limit of {0} km/h", selectedSpeedLimit);
		} else {
			filteredZones = null;
		}
	}
}
