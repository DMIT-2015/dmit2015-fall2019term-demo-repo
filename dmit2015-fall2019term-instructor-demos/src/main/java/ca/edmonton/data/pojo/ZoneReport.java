package ca.edmonton.data.pojo;

import lombok.Data;

@Data
public class ZoneReport {

	private String roadName;
	private int speedLimit;
	
	
	public ZoneReport(String roadName, int speedLimit) {
		super();
		this.roadName = roadName;
		this.speedLimit = speedLimit;
	}

}
