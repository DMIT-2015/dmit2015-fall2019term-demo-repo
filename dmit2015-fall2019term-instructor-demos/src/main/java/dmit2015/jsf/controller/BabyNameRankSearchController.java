package dmit2015.jsf.controller;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class BabyNameRankSearchController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BabyNamesController babyNamesController;
	
	@Getter @Setter
	private String queryName;
	
	@Getter @Setter
	private String queryGender;
	
	public void doSearch() {
		
	}

}
