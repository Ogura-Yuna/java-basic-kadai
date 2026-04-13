package com.example.springkadaiform.form;

import lombok.Data;

@Data
public class CalculatorForm {
	// 数値1
	private Integer num1;
	
	// 数値2
	private Integer num2;
	
	// 演算子
	private String operator;
}
