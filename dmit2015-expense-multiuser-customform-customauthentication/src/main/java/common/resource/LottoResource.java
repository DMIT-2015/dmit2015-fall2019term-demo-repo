package common.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("lotto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "lotto", description = "Your #1 source for lucky lottery numbers")
public class LottoResource {
	
	private static final Random rand = new Random();
	
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	@Operation(summary = "Greeting Mesage", description = "In plain text format")
	public String greetingPlainText() {
		return "Greetings from JAX-RS";
	}
	
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_HTML)
	@Operation(summary = "Greeting Message", description = "In HTML text format")
	public String greetingHtmlText() {
		return "Greetigs from <strong>JAX-RS</strong>";
	}
	
	@GET
	@Path("lotto649/singlenumber")
	@Operation(summary = "Get lucky lotto 649 number", description = "Return a single lotto 649 number")
	public int lotto649SingleNumber() {
		return getRandomNumber(1, 49);
	}

	@GET
	@Path("lottomax/singlenumber")
	@Operation(summary = "Get lucky lotto MAX number", description = "Return a single lotto MAX number")
	public int lottoMaxSingleNumber() {
		return getRandomNumber(1, 50);
	}

	@GET
	@Path("lotto649")
	@Operation(summary = "Get Lotto649 Quick Pick", description = "Return a single lotto 649 quick pick")
	public List<Integer> lotto649QuickPick() {
		Set<Integer> quickPickSet = new TreeSet<>();
		while (quickPickSet.size() < 6) {
			Integer randonNumber = getRandomNumber(1, 49);
			quickPickSet.add(randonNumber);
		}
		return new ArrayList<>(quickPickSet);
	}
	
	@GET
	@Path("lottomax")
	@Operation(summary = "Get LottoMax Quick Pick", description = "Return a single lotto Max quick pick")
	public List<Integer> lottoMaxQuickPick() {
		Set<Integer> quickPickSet = new TreeSet<>();
		while (quickPickSet.size() < 7) {
			Integer randonNumber = getRandomNumber(1, 50);
			quickPickSet.add(randonNumber);
		}
		return new ArrayList<>(quickPickSet);
	}
	
	@GET
	@Path("lotto649/quickpick")
	@Operation(summary = "Get multiple Lotto649 Quick Picks", description = "Return a collection lotto 649 quick picks")
	public List<List<Integer>> lotto649QuickPick(@QueryParam("draws") int draws) {
		List<List<Integer>> quickPicks = new ArrayList<>();
		for (int drawCount = 1; drawCount <= draws; drawCount += 1) {
			quickPicks.add( lotto649QuickPick() );
		}
		return quickPicks;
	}

	@GET
	@Path("lottomax/quickpick")
	@Operation(summary = "Get multiple LottoMAX Quick Picks", description = "Return a collection lotto MAX quick picks")
	public List<List<Integer>> lottoMaxQuickPick(@QueryParam("draws") int draws) {
		List<List<Integer>> quickPicks = new ArrayList<>();
		for (int drawCount = 1; drawCount <= draws; drawCount += 1) {
			quickPicks.add( lottoMaxQuickPick() );
		}
		return quickPicks;
	}

	@GET
	@Operation(summary = "Get random number between min and max", description = "Return a random number between min and maxs")
	public int randomNumber(@QueryParam("min") int minValue, @QueryParam("max") int maxValue) {
		return getRandomNumber(minValue, maxValue);
	}
	
	public static int getRandomNumber(int min, int max) {
		return rand.nextInt(max - min  + 1) + min;
	}
}
