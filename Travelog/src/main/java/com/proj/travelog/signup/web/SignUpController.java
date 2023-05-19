package com.proj.travelog.signup.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	@Autowired
	UserDao userDao;

	final int FAIL = 0;


	@InitBinder
	public void toDate(WebDataBinder binder) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록
		//	List<Validator> validatorList = binder.getValidators();
		//	System.out.println("validatorList="+validatorList);
	}

	@GetMapping("/add")
	public String signUp() {
		return "signup/signUpForm";
	}
	
	@PostMapping("/add")
	public String save(User user, BindingResult result, Model m) throws Exception {
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("이미 존재하는 아이디입니다~~~", "utf-8");
//			m.addAttribute("msg", msg);
//
//			return "redirect:/signup/add";
//		}
//		return "signup/signUpInfo";


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


	/*
	@PostMapping("/add")
	public String save(@Valid User user, BindingResult result, Model m) throws Exception {
		System.out.println("result="+result);
		System.out.println("user="+user);

		// User객체를 검증한 결과 에러가 있으면, registerForm을 이용해서 에러를 보여줘야 함.
		if(result.hasErrors()) {
			return "registerForm";
		}

		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
	*/
}
