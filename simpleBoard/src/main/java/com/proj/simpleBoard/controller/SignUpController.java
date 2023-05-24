package com.proj.simpleBoard.controller;

import com.proj.simpleBoard.domain.User;
import com.proj.simpleBoard.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	@Autowired
	UserDao userDao;

	final int FAIL = 0;


	@GetMapping("/add")
	public String signUp() {
		return "signup/signUpForm";
	}
	
	@PostMapping("/add")
	public String save(User user, BindingResult result, Model m) throws Exception {
		System.out.println("result=" + result);
		System.out.println("user=" + user);

		// User객체를 검증한 결과 에러가 있으면, registerForm을 이용해서 에러를 보여줘야 함.
		if(!result.hasErrors()) {		// 즉, 에러가 없을 때는 DB에 저장
			// 2. DB에 신규회원 정보를 저장
			int rowCnt = userDao.insertUser(user);

			if (rowCnt != FAIL) {            // 저장을 성공했을 때만 가입 정보를 보여줌
				return "signup/signUpInfo";
			}
		}

			return "signup/signUpForm";			// 저장하는 데 에러가 있던가, 실패했을 때 다시 signUpForm 으로!
	}

	private boolean isValid(User user) {
		return true;
	}
}