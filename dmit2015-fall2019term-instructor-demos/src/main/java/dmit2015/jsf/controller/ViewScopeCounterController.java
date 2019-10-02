package dmit2015.jsf.controller;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Named	// to access a object of this class from a JSF web page using EL in the format #{obj.property} 
@ViewScoped
public class ViewScopeCounterController implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private int requestCounter = 0;	// +getter +setter
	
	public void submit() {
		requestCounter++;
		Messages.addGlobalInfo("ViewScoped counter value is {0}", requestCounter);
	}

}
