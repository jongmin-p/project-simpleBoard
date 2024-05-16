package com.jmpark.simpleBoard.controller;

import com.jmpark.simpleBoard.domain.UserDto;
import com.jmpark.simpleBoard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired UserService userService;

    @GetMapping("")
    public String loginForm() {
        return "login/loginForm";
    }

    @PostMapping("")
    public String login() {
        return "index";
    }

    @PostMapping("/loginCheck")
    @ResponseBody
    public String loginCheck(@RequestParam("id") String id, @RequestParam("pwd") String pwd) throws Exception {

        UserDto searchResult = userService.read(id);

        // 1. 아이디가 존재하지 않을 때 (조회한 데이터가 없다는 뜻은 존재하지 않는 아이디라는 뜻)
        if (searchResult == null) {
            return "{\"retCode\" : \"Fail-ID\"}";

        // 2. 아이디는 존재하나, 로그인 시 입력한 비밀번호가 해당 아이디의 비밀번호와 일치하지 않을 때
        } else if (!searchResult.getUserPwd().equals(pwd)) {
            return "{\"retCode\" : \"Fail-PWD\"}";
        }

        // 3. 로그인 성공 (아이디 비밀번호 모두 검증 통과)
        return "{\"retCode\" : \"Success\"}";
    }
}