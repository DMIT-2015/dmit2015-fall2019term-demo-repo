package dmit2015.jsf.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;

@Named	// to access a object of this class from a JSF web page using EL in the format #{obj.property} 
@SessionScoped
public class SessionScopeCounterController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private int requestCounter = 0;	// +getter +setter
	
	public void submit() {
		requestCounter++;
		Messages.addGlobalInfo("Request counter value is {0}", requestCounter);
	}

}
