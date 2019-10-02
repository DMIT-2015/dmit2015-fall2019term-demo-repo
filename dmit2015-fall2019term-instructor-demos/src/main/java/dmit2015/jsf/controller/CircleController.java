package dmit2015.jsf.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Positive;

import org.omnifaces.util.Messages;

import dmit2015.demo03.Circle;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
public class CircleController {

//	@Getter @Setter
//	@Positive(message = "Radius must be a positive value")
//	private double radius;
	
	@Getter @Setter
	private Circle currentCircle;
	
	@PostConstruct
	void init() {
		currentCircle = new Circle();
	}
	
	public void submit() {
//		Circle circle = new Circle();
//		circle.setRadius(radius);
//		Messages.addGlobalInfo("Area: {0}", circle.area());
//		Messages.addGlobalInfo("Circumference: {0}", circle.circumference());		
		
		Messages.addGlobalInfo("Area: {0}", currentCircle.area());
		Messages.addGlobalInfo("Circumference: {0}", currentCircle.circumference());	
	}
	
}
