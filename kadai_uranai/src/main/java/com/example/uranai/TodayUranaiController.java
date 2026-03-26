package com.example.uranai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodayUranaiController {
	@GetMapping("/todayUranai")
    public String todayUranai() {
        return "todayUranai";
    }
	
	@GetMapping("/result")
    public String displayData(Model model) {
		HashMap<String, String> uranaiList = new HashMap<>() {
			{
				put("大吉", "新しいことに挑戦すると良い日です。");
				put("中吉", "落ち着いて行動すると良いでしょう。");
				put("小吉", "周囲との協力を大切にしましょう。");
				put("吉", "無理をしないことが大切です。");
				put("末吉", "慎重に過ごしましょう。");
			}
		};
		
		List<String> keyList = new ArrayList<>(uranaiList.keySet());
		String result = keyList.get(new Random().nextInt(keyList.size()));
        String detail = uranaiList.get(result);
		
		model.addAttribute("result", result);
		model.addAttribute("detail", detail);
		
        return "todayUranai";
    }
}
