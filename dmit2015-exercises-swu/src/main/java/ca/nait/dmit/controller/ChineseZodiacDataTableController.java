package ca.nait.dmit.controller;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import ca.nait.dmit.domain.ChineseZodiac;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ChineseZodiacDataTableController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private List<ChineseZodiac> zodiacs = new ArrayList<>();
	
	@PostConstruct
	void init() {
		for(int year = 1950; year < LocalDate.now().getYear(); year++) {
			ChineseZodiac singleZodiac = new ChineseZodiac();
			singleZodiac.setBirthYear(year);
			zodiacs.add(singleZodiac);
		}
	}

}
