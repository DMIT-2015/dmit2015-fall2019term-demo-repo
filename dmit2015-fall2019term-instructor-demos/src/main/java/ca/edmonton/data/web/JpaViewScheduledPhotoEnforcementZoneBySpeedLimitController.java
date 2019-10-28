package ca.edmonton.data.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import ca.edmonton.data.entity.ScheduledPhotoEnforcementZoneDetail;
import ca.edmonton.data.service.ScheduledPhotoEnforcementZoneDetailBean;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class JpaViewScheduledPhotoEnforcementZoneBySpeedLimitController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ScheduledPhotoEnforcementZoneDetailBean zoneBean;	

	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> filteredZones;

	@Getter @Setter
	private Integer selectedSpeedLimit;
	
	@Getter
	private List<Integer> distinctSpeedLimits;
	
	@PostConstruct
	void init() {
//		distinctSpeedLimits = spezdRepository.getDistinctSpeedLimits();
		distinctSpeedLimits = zoneBean.findDistinctSpeedLimits();
	}
	
	public void changeSpeedLimit() {
		if (selectedSpeedLimit != null) {
//			filteredZones = spezdRepository.getZonesBySpeedLimit(selectedSpeedLimit);
			filteredZones = zoneBean.findAllBySpeedLimit(selectedSpeedLimit);
			Messages.addGlobalInfo("Zones with a speed limit of {0} km/h", selectedSpeedLimit);
		} else {
			filteredZones = null;
		}
	}
}
