package com.example.uranai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StellaUranaiController {
	@GetMapping("/stellaUranai")
    public String stellaUranai(@RequestParam(name = "stella", required = false) String selectStella, Model model) {
		
		List<String> stellaList = new ArrayList<>(Arrays.asList(
					"おひつじ座", 
					"おうし座", 
					"ふたご座", 
					"かに座", 
					"しし座", 
					"おとめ座", 
					"てんびん座", 
					"さそり座", 
					"いて座", 
					"やぎ座", 
					"みずがめ座", 
					"うお座"
				));
		
		model.addAttribute("stellaList", stellaList);
		
		if(selectStella != null) {
			HashMap<String, String> uranaiList = new HashMap<>() {
				{
					put("大吉", "とても運気が良く、前向きに行動できる日です。");
					put("中吉", "落ち着いて行動すると良い流れに乗れます。");
					put("小吉", "小さな幸運を見つけられる日です。");
					put("吉", "焦らず自分のペースで進むと良い日です。");
					put("末吉", "無理をせず慎重に過ごすと安心です。");
				}
			};
			
			List<String> keyList = new ArrayList<>(uranaiList.keySet());
			String result = keyList.get(new Random().nextInt(keyList.size()));
	        String detail = uranaiList.get(result);
			
			model.addAttribute("selectStella", selectStella);
			model.addAttribute("result", result);
			model.addAttribute("detail", detail);
		}
		
        return "stellaUranai";
    }
}
