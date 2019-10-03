package ca.nait.dmit.controller;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import ca.nait.dmit.domain.ChineseZodiac;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Named
@ViewScoped
public class ChineseZodiacController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private ChineseZodiac currentChineseZodiac = new ChineseZodiac();
	
	@Getter
	private String animalImageUrl = "resources/images/chinese_zodiac.jpg";
	
	public void submit() {
		Messages.addGlobalInfo("You are a {0}", currentChineseZodiac.animal());
		animalImageUrl = String.format(
				"resources/images/zodiac_%s.jpg",
				currentChineseZodiac.animal().toLowerCase());
		
	}
}
