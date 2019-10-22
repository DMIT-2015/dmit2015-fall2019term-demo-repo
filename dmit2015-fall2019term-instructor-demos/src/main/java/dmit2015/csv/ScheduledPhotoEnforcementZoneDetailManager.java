package dmit2015.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class ScheduledPhotoEnforcementZoneDetailManager {

	@Getter
	private List<ScheduledPhotoEnforcementZoneDetail> zones = new ArrayList<>();

	public ScheduledPhotoEnforcementZoneDetailManager() throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/Scheduled_Photo_Enforcement_Zone_Details.csv"))) ) {
			String line;
			final String delimiter = ",";
			// Skip the first line as it is containing column headings
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(delimiter);
				ScheduledPhotoEnforcementZoneDetail zone = new ScheduledPhotoEnforcementZoneDetail();
				zone.setSiteId( Integer.parseInt(values[0]) );
				zone.setRoadName( values[1] );
				zone.setLocationDescription( values[2] );
				zone.setFromPoint(values[4]);
				zone.setToPoint(values[5]);
				zone.setSpeedLimit( Integer.parseInt(values[6]) );
				zones.add(zone);
			}			
		}
		
	}
	
	public List<ScheduledPhotoEnforcementZoneDetail> getZonesOrderBySpeedLimit() {
		Comparator<ScheduledPhotoEnforcementZoneDetail> speedLimitComparator = (lhs, rhs) -> Integer.compare(lhs.getSpeedLimit(),  rhs.getSpeedLimit());
		return zones.stream().sorted(speedLimitComparator).collect(Collectors.toList());
	}
	
	public List<ScheduledPhotoEnforcementZoneDetail> getZonesOrderBySpeedLimitDescending() {
		Comparator<ScheduledPhotoEnforcementZoneDetail> speedLimitComparator = (lhs, rhs) -> Integer.compare(lhs.getSpeedLimit(),  rhs.getSpeedLimit());
		return zones.stream().sorted(speedLimitComparator.reversed()).collect(Collectors.toList());
	}

	public List<ScheduledPhotoEnforcementZoneDetail> getZonesOrderByRoadNameThenLocationDescription() {
		Comparator<ScheduledPhotoEnforcementZoneDetail> roadNameComparator = (lhs, rhs) -> lhs.getRoadName().compareToIgnoreCase(rhs.getRoadName());
		Comparator<ScheduledPhotoEnforcementZoneDetail> locationDescriptionComparator = (lhs, rhs) -> lhs.getLocationDescription().compareToIgnoreCase(rhs.getLocationDescription());
		return zones.stream().sorted(roadNameComparator.thenComparing(locationDescriptionComparator)).collect(Collectors.toList());
	}

	public List<ScheduledPhotoEnforcementZoneDetail> getZonesBySpeedLimit(int speedLimit) {
		return zones.stream().filter(instance -> instance.getSpeedLimit() == speedLimit).collect(Collectors.toList());
	}

	public List<ScheduledPhotoEnforcementZoneDetail> getZonesByRoadName(String roadName) {
		return zones.stream().filter(instance -> instance.getRoadName().equalsIgnoreCase(roadName)).collect(Collectors.toList());
	}

	public List<ScheduledPhotoEnforcementZoneDetail> getZonesBySpeedLimitAndRoadName(int speedLimit, String roadName) {
		return zones.stream()
				.filter(instance -> instance.getSpeedLimit() == speedLimit)
				.filter(instance -> instance.getRoadName().equalsIgnoreCase(roadName))
				.collect(Collectors.toList());
	}
	
	public List<Integer> getDistinctSpeedLimits() {
		return zones.stream().map(ScheduledPhotoEnforcementZoneDetail::getSpeedLimit).distinct().sorted().collect(Collectors.toList());
	}

	public List<Integer> getDistinctSpeedLimitsOrderedDescending() {
		return zones.stream().map(ScheduledPhotoEnforcementZoneDetail::getSpeedLimit).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	}

	public List<String> getDistinctRoadNames() {
		return zones.stream().map(ScheduledPhotoEnforcementZoneDetail::getRoadName).distinct().sorted().collect(Collectors.toList());
	}

}
