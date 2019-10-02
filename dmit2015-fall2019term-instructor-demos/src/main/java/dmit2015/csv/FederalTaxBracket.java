package dmit2015.csv;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FederalTaxBracket {

	@JsonProperty("Tax Year")
	private int taxYear;
	
	@JsonProperty("BracketNo")
	private int bracketNo;
	
	@JsonProperty("Minimum Income")
	private double minimumIncome;
	
	@JsonProperty("Maximum Income")
	private double maximimIncome;
	
	@JsonProperty("Tax Rate")
	private double taxRate;
	
	@JsonProperty("Base Tax Amount")
	private int baseTaxAmount;
	
	public double taxAmount(double taxableIncome) {
		return (taxableIncome - minimumIncome) * taxRate + baseTaxAmount;
	}
}
