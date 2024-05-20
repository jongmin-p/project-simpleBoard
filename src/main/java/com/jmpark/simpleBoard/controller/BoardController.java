package com.jmpark.simpleBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/list")
    public String list(HttpServletRequest request) {

        // "Board" 탭을 눌렀을때
        // 로그인 체크해서, 로그인 안했다면, /login 페이지로 이동
        if(!loginCheck(request)) {
            return "redirect:/login?toURL=" + request.getRequestURL();
        }

        return "board/list";
    }


    // 로그인 했는 지 검사하는 메서드
    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();


        // 2. 세션에 id 가 있는지 확인 (null 이 아니면, 로그인 상태)
        return session.getAttribute("id") != null;
    }
}