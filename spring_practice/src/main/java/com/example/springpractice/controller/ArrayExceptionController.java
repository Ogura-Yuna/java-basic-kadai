package com.example.springpractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArrayExceptionController {
	@GetMapping("/array")
	public String arrayException(@RequestParam int num) {
		String[] dummyArray = {"侍 一郎", "侍 二郎", "侍 三郎"};
		
		// 配列の指定番号を出力(3以上だと例外が発生)
		return dummyArray[num];
	}
}
