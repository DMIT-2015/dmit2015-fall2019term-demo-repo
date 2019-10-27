package ca.edmonton.data;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import lombok.Getter;

@Named
@ViewScoped
public class JpaViewScheduledPhotoEnforcementZoneBySpeedLimitAndRoadNameController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ScheduledPhotoEnforcementZoneDetailBean zoneBean;
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> filteredZones;

	@Inject
	private JpaViewScheduledPhotoEnforcementZoneBySpeedLimitController speedLimitController;
	
	@Inject
	private JpaViewScheduledPhotoEnforcementZoneByRoadNameController roadNameController;
			
	@PostConstruct
	void init() {
	
	}
	
	public void filterZones() {
		Integer selectedSpeedLimit = speedLimitController.getSelectedSpeedLimit();
		String selectedRoadName = roadNameController.getSelectedRoadName();
		
		if (selectedSpeedLimit != null && !selectedRoadName.isBlank()) {
			filteredZones = zoneBean.findAllBySpeedLimitAndRoadName(selectedSpeedLimit, selectedRoadName);		
			Messages.addGlobalInfo("Zones with a speed limit of {0} km/h and at {1}", selectedSpeedLimit, selectedRoadName);
		} else if (selectedSpeedLimit != null && selectedRoadName.isBlank()) {
			filteredZones = zoneBean.findAllBySpeedLimit(selectedSpeedLimit);
			Messages.addGlobalInfo("Zones with a speed limit of {0} km/h", selectedSpeedLimit);
		} else if (selectedSpeedLimit == null && !selectedRoadName.isBlank()) {
			filteredZones = zoneBean.findAllByRoadName(selectedRoadName);
			Messages.addGlobalInfo("Zones for {0}", selectedRoadName);
		} else {
			filteredZones = null;
		}
	}
}
