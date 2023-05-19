package com.proj.travelog.board.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/board")
public class BoardController {
	@GetMapping("/list")
	public String list(HttpServletRequest request) {
		if(!loginCheck(request)) {
			// 로그인 화면(login/login) 에게 GET 방식으로 board/list 라는 주소를 보냄.
			// 여기서 board/list 라는 주소를 보내면, 이제 loginForm.jsp 에서 받아야 함.
								// toURL 이란? (단지, loginForm.jsp 에서 받기 위한 파라미터명 설정인 듯)
			return "redirect:/login/login?toURL=" + request.getRequestURL();
		}

		return "board/boardList";
	}


	// 로그인 체크 하는 방법?
	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션을 얻어서
		HttpSession session = request.getSession();
		
		// 2. 세션에 id 가 있는지 확인
//		if(session.getAttribute("id") != null) {
//			return true;
//		} else {
//			return false;
//		}
		
		// 세션에 id 가 있으면 true 를, 없으면 false 를 반환  (로그인 시, LoginController 에서 세션을 저장해야 함)
		return session.getAttribute("id") != null;
	}
	
	
	
	@GetMapping("/detail")
	public String boardDetail() {
		return "board/boardDetail";
	}
	
	@GetMapping("/insert")
	public String insertBoard() {
		return "board/insertBoard";
	}
	
	@GetMapping("/update")
	public String updateBoard() {
		return "board/updateBoard";
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
