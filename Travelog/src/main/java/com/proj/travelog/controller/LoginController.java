package com.proj.travelog.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.proj.travelog.domain.User;
import com.proj.travelog.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserDao userDao;

	@GetMapping("/login")
	public String login() {
		return "login/loginForm";
	}
	
	// 로그아웃 기능
	@GetMapping("logout")		// 세션은 HttpServletRequest 로부터 얻어도 되지만, 직접 써도 됨.
	public String logout(HttpSession session) {
		// 1. 세션 종료
		session.invalidate();
		
		// 2. 홈으로 이동
		return "redirect:/";
	}

	
	// 로그인 유효성 검사
	@PostMapping("login")
	public String login(String id, String pwd, boolean rememberId, String toURL,
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(id);
		System.out.println(pwd);
		System.out.println(rememberId);
		
		// 1. 유효성 검사 (id 와 비밀번호 일치하는지)
		if(!isValid(id, pwd)) {
			// 2-1. 일치하지 않으면 loginForm 으로 이동!
			String msg = URLEncoder.encode("ID 또는 비밀번호가 일치하지 않습니다.", "utf-8");
			return "redirect:/login/login?msg=" + msg;
		}

		
		// 아이디/비번이 일치하면, Session 에다가도 id를 저장해주자  (BoardController 에서 쓸거임)
		// 1. 세션 객체 얻어오기.
		HttpSession session = request.getSession();
		// 2. 세션 객체에 id를 저장
		session.setAttribute("id", id);
		

		/// 그리고 아이디 비번이 일치하고,
		if(rememberId == true) {
			// 2-2. 아이디 저장에 체크가 돼 있으면 
			// 2-2-1. 쿠키를 생성.
			Cookie cookie = new Cookie("id", id);		// cookie 의 id 는 넘어온 id(사용자가 로그인할 떄 쓴) 값으로 준다!
			
			// 2-2-2. 응답에 저장.
			response.addCookie(cookie);
			
			// 2-2-3. 홈으로 이동.
			// return "redirect:/";
			
			// toURL 이 null 이거나 빈 문자열이면 home 으로 이동, 그렇지 않으면 자기 자신으로 이동(현재는 /board/list)
			// 현재, loginForm.jsp 에서 hidden 으로 toURL 받아옴.
			toURL = (toURL == null || toURL == "") ? "/" : toURL;
			return "redirect:" + toURL;

		} else {
			
			// 2-2. 아이디 저장에 체크돼 있지 않으면
			// 2-2-1. 쿠키를 삭제 (혹시 기존에 있던게 있을 수 있으니, 만들지 않는것보다는 아예 삭제 해주기)
			Cookie cookie = new Cookie("id", id);
			
			cookie.setMaxAge(0);		// 유효 기간을 0으로
			
			// 2-2-2. 응답에 저장.
			response.addCookie(cookie);
			
			// 2-2-3. 홈으로 이동.
			// return "redirect:/";
			
			// toURL 이 null 이거나 빈 문자열이면 home 으로 이동, 그렇지 않으면 자기 자신으로 이동(현재는 /board/list)
			// 현재, loginForm.jsp 에서 hidden 으로 toURL 받아옴.
			toURL = (toURL == null || toURL == "") ? "/" : toURL;
			return "redirect:" + toURL;
		}
	}

	
	private boolean isValid(String id, String pwd) throws Exception {
		// return "asdf".equals(id) && "1234".equals(pwd);

		User user = userDao.selectUser(id);

		// user 가 null 이면 없다는 뜻이니까 false 리턴
		if(user == null) return false;

		// user 가 null 이 아니면, 패스워드가 같아야 함 (그 결과를 return)
		return user.getPwd().equals(pwd);
	}


	// 아이디 찾기
	@GetMapping("findId")
	public String findId() {

		return "login/findIdForm";
	}

	// 비번 찾기
	@GetMapping("findPwd")
	public String findPwd() {

		return "login/findPwdForm";
	}
}