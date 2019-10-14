package dmit2015.csv;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ScheduledPhotoEnforcementZoneDetailCsvParserTest {

	static ScheduledPhotoEnforcementZoneDetailManager zoneManager;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		zoneManager = new ScheduledPhotoEnforcementZoneDetailManager();
	}
	
	@Test
	public void shouldOrderBySpeedLimit() {
		// Print the road name, location description, and speed limit
		System.out.println("Scheduled Photo Enforcement Zones by Speed Limit");
		String header = String.format("|%-25s|%-50s|%10s", "Road Name","Location Description","Speed Limit");
		System.out.println(header);
		zoneManager.getZonesOrderBySpeedLimit().forEach( instance -> {
			String message = String.format("|%-25s|%-50s|%10d", instance.getRoadName(), instance.getLocationDescription(), instance.getSpeedLimit());
			System.out.println(message);
		});
	}

	@Test
	public void shouldOrderBySpeedLimitDescending() {
		// Print the road name, location description, and speed limit
		System.out.println("Scheduled Photo Enforcement Zones by descending Speed Limit");
		String header = String.format("|%-25s|%-50s|%10s", "Road Name","Location Description","Speed Limit");
		System.out.println(header);
		zoneManager.getZonesOrderBySpeedLimitDescending().forEach( instance -> {
			String message = String.format("|%-25s|%-50s|%10d", instance.getRoadName(), instance.getLocationDescription(), instance.getSpeedLimit());
			System.out.println(message);
		});
	}

	@Test
	public void shouldFindDistinctSpeedLimit() {
		assertEquals(8, zoneManager.getDistinctSpeedLimits().size());
	}

	@Test
	public void shouldFindZonesBySpeedLimit() {
		assertEquals(69, zoneManager.getZonesBySpeedLimit(30).size());
		assertEquals(77, zoneManager.getZonesBySpeedLimit(50).size());
		assertEquals(22, zoneManager.getZonesBySpeedLimit(60).size());
		assertEquals(6, zoneManager.getZonesBySpeedLimit(80).size());
		assertEquals(17, zoneManager.getZonesBySpeedLimit(100).size());

	}

}
