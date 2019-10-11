package dmit2015.csv;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduledPhotoEnforcementZoneDetail {

	@JsonProperty("Site ID")
	private String siteId;

	@JsonProperty("Road Name")
	private String roadName;
	
	@JsonProperty("Location Description")
	private String locationDescription;
	
	@JsonProperty("Direction")
	private String direction;
	
	@JsonProperty("From Point")
	private String fromPoint;
	
	@JsonProperty("To Point")
	private String toPoint;
	
	@JsonProperty("Speed Limit")
	private int speedLimit;
	
	@JsonProperty("Latitude")	
	private double latitude;

	@JsonProperty("Longitude")	
	private double longitude;
	
//	@JsonProperty("Geo Location")	
//	private String geoLocation;
}
