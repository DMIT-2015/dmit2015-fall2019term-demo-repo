package dmit2015.csv;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class FederalTaxParser {

	List<FederalTaxBracket> federalTaxBrackets;
	
	public void run() throws IOException {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = CsvSchema.emptySchema().withHeader();
		MappingIterator<FederalTaxBracket> iter = mapper
				.readerFor(FederalTaxBracket.class)
				.with(schema)
				.readValues(getClass().getResourceAsStream("/FederalTax.csv"));
		federalTaxBrackets = iter.readAll();
		
		double income = 23815;
		for(FederalTaxBracket taxBracket : federalTaxBrackets) {
			System.out.println(taxBracket);
			if (taxBracket.getTaxYear() == 2019
					&& income >= taxBracket.getMinimumIncome() && income <= taxBracket.getMaximimIncome()) {
				System.out.println(taxBracket.taxAmount(income));
				break;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		FederalTaxParser program = new FederalTaxParser();
		program.run();
	}

}
