package com.example.springpractice.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ButtonPressEventListener {
	// ボタン押下回数
	private int count;

	@EventListener
	public void onButtonPressEvent(ButtonPressEvent event) {
		this.count++;
		System.out.println("ボタンが" + count +"回押されました！");
	}
}