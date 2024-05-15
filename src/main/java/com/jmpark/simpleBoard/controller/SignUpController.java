package com.jmpark.simpleBoard.controller;

import com.jmpark.simpleBoard.domain.UserDto;
import com.jmpark.simpleBoard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public String signUpForm() {
        return "signup/signUpForm";
    }


//    @PostMapping("")
//    public String signUp(HttpServletRequest request) throws Exception {
//        String userId    = request.getParameter("id");
//        String userPwd   = request.getParameter("pwd");
//        String userName  = request.getParameter("name");
//        String userPhone = request.getParameter("phone");
//        String userEmail = request.getParameter("email");
//        char status = request.getParameter("status").charAt(0);
//        char isUser = request.getParameter("isUser").charAt(0);
//
//        UserDto userDto = new UserDto();
//        userDto.setUserId(userId);
//        userDto.setUserPwd(userPwd);
//        userDto.setUserName(userName);
//        userDto.setUserPhone(userPhone);
//        userDto.setUserEmail(userEmail);
//        userDto.setStatus(status);
//        userDto.setIsUser(isUser);
//
//        userService.write(userDto);
//
//        return "signUpSuccess";
//    }


    // 위의 코드는 손으로 직접 request.getParameter 으로 꺼내와서 setter 로 UserDto 에 입력시킨 거고,
// 아래는 @ModelAttribute 를 이용해서 자동으로 바인딩 되도록 함.
    // 대신 @ModelAttribute 쓰려면, jsp 파일에서 input 태그의 name 명을 userDto 의 변수명이랑 일치시켜야 함.
    @PostMapping("")
    public String signUp(@ModelAttribute("userDto") UserDto userDto, Model m) throws Exception {
        userService.write(userDto);

        m.addAttribute("id", userDto.getUserId());

        return "/signup/signUpSuccess";
    }


    // 회원가입 시, 아이디 공백 및 중복 체크
    @PostMapping("/idCheck")
    @ResponseBody
    public String idCheck(@RequestParam("id") String id) throws Exception {
//        System.out.println("req => " + req);
//
//        String id = req.getParameter("id");

        System.out.println("id => " + id);


        // 사용자가 입력한 아이디가 기존에 있는 아이디인지 검사
        String searchResult = userService.read(id);

        // 아이디 입력 칸에 아무것도 입력 안했을 때
        if (id.equals("")) {
            return "{\"retCode\" : \"Blank\"}";

            // 아이디가 정규식에 부합하지 않을 때
        } else if (!regexCheck(id)) {
            return "{\"retCode\" : \"RegexCheck\"}";

            // 아이디가 중복일 때 (이미 해당 아이디가 존재할 때)
        } else if (searchResult != null) {
            return "{\"retCode\" : \"Fail\"}";
        }

        // 아이디 중복 아님. (해당 아이디 기존에 없음)
        return "{\"retCode\" : \"Success\"}";
    }


    // 아이디 정규식 검사
    private boolean regexCheck(String id) {
        // 정규식 패턴 생성
        String pattern = "^[a-zA-Z0-9]{5,15}$";

        // 패턴에 대한 Matcher 생성
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(id);

        // 조건 검사
        if (!matcher.matches()) {           // 올바른 형식이 아님!
            return false;
        } else {                            // 형식에 적합함.
            return true;
        }
    }
}