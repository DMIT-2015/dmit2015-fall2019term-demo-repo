package ca.edmonton.data.entity;

import java.io.Serializable;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduledPhotoEnforcementZoneDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonProperty("Site ID")
	private Integer siteId;

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
	
//	private String geoLocation;
}
