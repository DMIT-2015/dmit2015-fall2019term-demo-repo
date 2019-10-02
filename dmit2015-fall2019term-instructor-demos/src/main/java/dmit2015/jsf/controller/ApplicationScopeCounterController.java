package dmit2015.jsf.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;

@Named	// to access a object of this class from a JSF web page using EL in the format #{obj.property} 
@ApplicationScoped
public class ApplicationScopeCounterController {
	
	@Getter @Setter
	private int requestCounter = 0;	// +getter +setter
	
	public void submit() {
		requestCounter++;
		Messages.addGlobalInfo("ApplicationScoped counter value is {0}", requestCounter);
	}

}
