package com.proj.travelog.signup.web;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
 @RequestMapping("/signup")
public class SignUpController {

	@GetMapping("/add")
	public String signUp() {
		return "signup/signUpForm";
	}
	
	@PostMapping("/save")
	public String save(User user, Model m) throws Exception {
		if(!isValid(user)) {
			String msg = URLEncoder.encode("이미 존재하는 아이디입니다~~~", "utf-8");
			m.addAttribute("msg", msg);
			
			return "redirect:/signup/add";
		}
		return "signup/signUpInfo";
	}


	private boolean isValid(User user) {
		return false;
	}
}
