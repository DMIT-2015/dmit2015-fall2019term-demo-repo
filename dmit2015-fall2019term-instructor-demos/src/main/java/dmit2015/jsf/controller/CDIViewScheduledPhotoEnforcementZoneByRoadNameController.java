package dmit2015.jsf.controller;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import dmit2015.csv.ScheduledPhotoEnforcementZoneDetail;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CDIViewScheduledPhotoEnforcementZoneByRoadNameController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CDIViewScheduledPhotoEnforcementZonesController vspepezController;
	
	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> zones;

	@Getter @Setter
	private String selectedRoadName;
		
	@PostConstruct
	void init() {
		
	}
	
	public void changeRoadName() {
		if (selectedRoadName != null) {
			zones = vspepezController.getZones()
					.stream()
					.filter(instance -> instance.getRoadName().equalsIgnoreCase(selectedRoadName))
					.collect(Collectors.toList());
			Messages.addGlobalInfo("Zones for {0}", selectedRoadName);
		} else {
			zones = null;
		}
	}
}
