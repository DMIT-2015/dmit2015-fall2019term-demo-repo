package ca.edmonton.data;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ScheduledPhotoEnforcementZoneDetail {

	@Id
	private Integer siteId;

	private String roadName;
	
	private String locationDescription;
	
	private String direction;
	
	private String fromPoint;
	
	private String toPoint;
	
	private int speedLimit;
	
	private double latitude;

	private double longitude;
	
//	private String geoLocation;
}
