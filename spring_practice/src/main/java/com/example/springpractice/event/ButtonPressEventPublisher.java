package com.example.springpractice.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ButtonPressEventPublisher {
	
	private final ApplicationEventPublisher applicationEventPublisher;
	
	public ButtonPressEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		
		this.applicationEventPublisher = applicationEventPublisher;
	
	}
	
	public void publishButtonPressEvent() {
	
		ButtonPressEvent event = new ButtonPressEvent(this);
		
		applicationEventPublisher.publishEvent(event);
	
	}
}