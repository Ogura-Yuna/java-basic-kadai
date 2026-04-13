package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springkadaiform.form.CalculatorForm;

@Controller
public class CalculatorController {
	@GetMapping("/calculator")
	public String showForm(Model model) {
		
		model.addAttribute("calculatorForm", new CalculatorForm());
		
		return "calculator";
	}
	
	@PostMapping("/calculator")
	public String calculate(@ModelAttribute CalculatorForm calculatorForm, Model model) {
		
		Integer result = null;
		String errorMsg = null;
		
		try {
			
			Integer num1 = calculatorForm.getNum1();
			Integer num2 = calculatorForm.getNum2();
			String operator = calculatorForm.getOperator();
			
			if(num1 == null || num2 == null) {
				throw new NullPointerException("未入力エラー");
			}
			
			switch (operator) {
				case "+":
					result = num1 + num2;
					break;
					
				case "-":
					result = num1 - num2;
					break;
					
				case "*":
					result = num1 * num2;
					break;
					
				case "/":
					result = num1 / num2;
					break;
					
				default:
					errorMsg = "無効な演算子です。";
			}
		} catch (Exception e) {
			errorMsg = "正しく計算できませんでした。";
		}
		model.addAttribute("result", result);
		model.addAttribute("errorMsg", errorMsg);
		return "calculator";
		
	}
}
