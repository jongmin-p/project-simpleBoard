package com.proj.travelog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.proj.travelog.domain.BoardDto;
import com.proj.travelog.domain.PageHandler;
import com.proj.travelog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;


	@GetMapping("/list")
	public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			// 로그인 화면(login/login) 에게 GET 방식으로 board/list 라는 주소를 보냄.
			// 여기서 board/list 라는 주소를 보내면, 이제 loginForm.jsp 에서 받아야 함.
								// toURL 이란? (단지, loginForm.jsp 에서 받기 위한 파라미터명 설정인 듯)
			return "redirect:/login/login?toURL=" + request.getRequestURL();
		}

		if(page == null) page = 1;
		if(pageSize == null) pageSize = 10;


			try {
				// 페이지 네비게이션 바 만들기
				int totalCnt = boardService.getCount();
				PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);


				Map map = new HashMap();
				map.put("offset", (page - 1) * pageSize);
				map.put("pageSize", pageSize);

				// 게시판 목록 Model 로 전달 (boardList.jsp 에서 받아 쓰기)
				List<BoardDto> list = boardService.getPage(map);
				m.addAttribute("li", list);

				// 페이징 네비게이션바 도 Model로 전달
				m.addAttribute("ph", pageHandler);
			} catch (Exception e) {
				e.printStackTrace();
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
