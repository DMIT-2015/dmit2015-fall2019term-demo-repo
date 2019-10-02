package dmit2015.jsf.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;

@Named	// to access a object of this class from a JSF web page using EL in the format #{obj.property} 
@RequestScoped
public class RequestScopeCounterController {
	
	@Getter @Setter
	private int requestCounter = 0;	// +getter +setter

	public String submitAction() {
		Messages.addGlobalInfo("Action message");
		return null;
	}

	public String submitActionRedirect() {
		Messages.addGlobalInfo("Action message");
		return "/index?faces-redirect=true";
	}

	public void submitActionListener() {
		Messages.addGlobalInfo("ActionListener message");		
	}

	public void submit() {
		requestCounter++;
		Messages.addGlobalInfo("RequestScoped counter value is {0}", requestCounter);
	}

}
