package ca.edmonton.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ScheduledPhotoEnforcementZoneDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
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
