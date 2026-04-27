package com.example.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.springpractice.event.ButtonPressEventPublisher;

@Controller
public class EventController {
	
	private final ButtonPressEventPublisher eventPublisher;

	public EventController(ButtonPressEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}
	
	@GetMapping("/event")
	public String event() {
	
		return "eventView";
	
	}
	
	@GetMapping("/pressButton")
	public String pressButton() {
		
		eventPublisher.publishButtonPressEvent();
		
		return "redirect:/event";
    }
}