package com.example.uranai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopMenuController {
	@GetMapping("/")
    public String topMenu() {
        return "topMenu";
    }
}
