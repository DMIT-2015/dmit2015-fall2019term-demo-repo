package ca.nait.dmit.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import ca.nait.dmit.domain.Rectangle;
import lombok.Getter;
import lombok.Setter;

@Named	// create a CDI managed bean 
@RequestScoped
public class RectangleController {
	
	@Getter @Setter
	private Rectangle currentRectangle = new Rectangle();
	
	public void submit() {
		// Send a Info messages to FacesContext with the area
		// and perimeter of the rectangle
		Messages.addGlobalInfo("Area = {0}, Perimeter = {1}", 
				currentRectangle.area(), currentRectangle.perimeter());
		// clear the form fields with new values
		currentRectangle = new Rectangle();
		
	}

}
