package dmit2015.jsf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import dmit2015.csv.FederalTaxBracket;
import dmit2015.csv.NameRank;
import lombok.Getter;

@Named
@ApplicationScoped
public class BabyNamesController {
	
	@Getter
	private List<NameRank> names;
	
	@PostConstruct
	void init() {
		CsvMapper mapper = new CsvMapper();
//		CsvSchema schema = CsvSchema.emptySchema().withoutHeader();
		CsvSchema schema = CsvSchema.builder()
				.addColumn("name")
				.addColumn("gender")
				.addColumn("nameCount")
				.build();
		try {
			MappingIterator<NameRank> iter = mapper
					.readerFor(NameRank.class)
					.with(schema)
					.readValues(getClass().getResourceAsStream("/yob2018.txt"));
			names = iter.readAll();
		} catch (Exception e) {
			names = new ArrayList<>();
		}
	}

}
