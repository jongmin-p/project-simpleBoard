package com.proj.travelog.board.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	@GetMapping("/boardList")
	public String boardList() {
		return "board/boardList";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "login/loginForm";
	}
	
	@GetMapping("/signUpForm")
	public String signUpForm() {
		return "login/signUpForm";
	}
	
	@GetMapping("/findIdForm")
	public String findIdForm() {
		return "login/findIdForm";
	}
	
	@GetMapping("/findPwdForm")
	public String findPwdForm() {
		return "login/findPwdForm";
	}
}
