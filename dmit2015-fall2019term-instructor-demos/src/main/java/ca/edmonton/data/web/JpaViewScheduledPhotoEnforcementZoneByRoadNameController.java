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
public class JpaViewScheduledPhotoEnforcementZoneByRoadNameController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ScheduledPhotoEnforcementZoneDetailBean zoneBean;
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> filteredZones;

	@Getter @Setter
	private String selectedRoadName;
		
	@PostConstruct
	void init() {
	
	}
	
	public void changeRoadName() {
		if (selectedRoadName != null) {
			filteredZones = zoneBean.findAllByRoadName(selectedRoadName);
			Messages.addGlobalInfo("Zones for {0}", selectedRoadName);
		} else {
			filteredZones = null;
		}
	}
}
