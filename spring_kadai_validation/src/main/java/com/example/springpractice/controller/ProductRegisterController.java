package com.example.springpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springpractice.form.ProductRegisterForm;

@Controller
public class ProductRegisterController {
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("productRegisterForm", new ProductRegisterForm());
		return "productRegisterView";
	}
	
	@PostMapping("/register")
	public String registerProduct(Model model, @Validated ProductRegisterForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "productRegisterView";
		}
		
		model.addAttribute("message", "登録が完了しました。");
		return "productRegisterView";
	}
}
