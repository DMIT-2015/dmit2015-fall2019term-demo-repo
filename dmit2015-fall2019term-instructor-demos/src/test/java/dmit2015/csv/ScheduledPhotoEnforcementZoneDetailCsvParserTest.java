package dmit2015.csv;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.ColumnType;

public class ScheduledPhotoEnforcementZoneDetailCsvParserTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Test
	public void testGetRoadName() throws IOException {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		MappingIterator<ScheduledPhotoEnforcementZoneDetail> iter = mapper
				.readerFor(ScheduledPhotoEnforcementZoneDetail.class)
				.with(schema)
				.readValues(getClass().getResourceAsStream("/Scheduled_Photo_Enforcement_Zone_Details.csv"));
		List<ScheduledPhotoEnforcementZoneDetail> zones = iter.readAll();
		assertEquals(198, zones.size());

		// Print each object in the collection to the Console
//		zones.forEach(System.out::println);
		
		// Sort the list ascending by the speed limit
		Comparator<ScheduledPhotoEnforcementZoneDetail> speedLimitAscendingComparator =
			(lhs, rhs) -> lhs.getSpeedLimit() - rhs.getSpeedLimit();
		List<ScheduledPhotoEnforcementZoneDetail> sortedAscendingZones
			= zones.stream().sorted(speedLimitAscendingComparator).collect(Collectors.toList());
		sortedAscendingZones.forEach(System.out::println);

		// Sort the list descending by the speed limit
		Comparator<ScheduledPhotoEnforcementZoneDetail> speedLimitDecendingComparator =
				(lhs, rhs) -> rhs.getSpeedLimit() - lhs.getSpeedLimit();
		List<ScheduledPhotoEnforcementZoneDetail> sortedDecendingZones
				= zones.stream().sorted(speedLimitDecendingComparator).collect(Collectors.toList());
		sortedDecendingZones.forEach(System.out::println);
	}

}
