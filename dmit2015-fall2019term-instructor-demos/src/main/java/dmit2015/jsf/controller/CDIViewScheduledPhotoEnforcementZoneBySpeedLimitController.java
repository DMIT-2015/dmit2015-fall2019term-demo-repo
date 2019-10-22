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
import lombok.Setter;

@Named
@ViewScoped
public class CDIViewScheduledPhotoEnforcementZoneBySpeedLimitController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ScheduledPhotoEnforcementZoneDetailRepository spezdRepository;
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> filteredZones;

	@Getter @Setter
	private Integer selectedSpeedLimit;
	
	@Getter
	private List<Integer> distinctSpeedLimits;
	
	@PostConstruct
	void init() {
		distinctSpeedLimits = spezdRepository.getDistinctSpeedLimits();
	}
	
	public void changeSpeedLimit() {
		if (selectedSpeedLimit != null) {
			filteredZones = spezdRepository.getZonesBySpeedLimit(selectedSpeedLimit);
			Messages.addGlobalInfo("Zones with a speed limit of {0} km/h", selectedSpeedLimit);
		} else {
			filteredZones = null;
		}
	}
}
