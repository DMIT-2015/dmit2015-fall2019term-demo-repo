package dmit2015.csv;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FederalTaxBracket {

	@JsonProperty("")
	private int taxYear;
	
	private int bracketNo;
	
	private double minimumIncome;
	
	private double maximimIncome;
	
	private double taxRate;
	
	private int baseTaxAmount;
	
	public double taxAmount(double taxableIncome) {
		return (taxableIncome - minimumIncome) * taxRate + baseTaxAmount;
	}
}
