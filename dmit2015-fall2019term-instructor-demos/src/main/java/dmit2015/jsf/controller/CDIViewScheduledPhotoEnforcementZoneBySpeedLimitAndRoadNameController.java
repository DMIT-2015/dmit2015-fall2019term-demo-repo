package dmit2015.jsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import dmit2015.csv.ScheduledPhotoEnforcementZoneDetail;
import dmit2015.csv.ScheduledPhotoEnforcementZoneDetailRepository;
import lombok.Getter;

@Named
@ViewScoped
public class CDIViewScheduledPhotoEnforcementZoneBySpeedLimitAndRoadNameController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ScheduledPhotoEnforcementZoneDetailRepository spezdRepository;
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> filteredZones;

	@Inject
	private CDIViewScheduledPhotoEnforcementZoneBySpeedLimitController speedLimitController;
	
	@Inject
	private CDIViewScheduledPhotoEnforcementZoneByRoadNameController roadNameController;
			
	@PostConstruct
	void init() {
	
	}
	
	public void filterZones() {
		Integer selectedSpeedLimit = speedLimitController.getSelectedSpeedLimit();
		String selectedRoadName = roadNameController.getSelectedRoadName();
		
		if (selectedSpeedLimit != null && !selectedRoadName.isBlank()) {
			filteredZones = spezdRepository.getZonesBySpeedLimitAndRoadName(selectedSpeedLimit, selectedRoadName);
			Messages.addGlobalInfo("Zones with a speed limit of {0} km/h and at {1}", selectedSpeedLimit, selectedRoadName);
		} else if (selectedSpeedLimit != null && selectedRoadName.isBlank()) {
			filteredZones = spezdRepository.getZonesBySpeedLimit(selectedSpeedLimit);
			Messages.addGlobalInfo("Zones with a speed limit of {0} km/h", selectedSpeedLimit);
		} else if (selectedSpeedLimit == null && !selectedRoadName.isBlank()) {
			filteredZones = spezdRepository.getZonesByRoadName(selectedRoadName);
			Messages.addGlobalInfo("Zones for {0}", selectedRoadName);
		} else {
			filteredZones = null;
		}
	}
}
