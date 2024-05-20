package com.jmpark.simpleBoard.controller;

import com.jmpark.simpleBoard.domain.UserDto;
import com.jmpark.simpleBoard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired UserService userService;

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 1. 세션 종료
        session.invalidate();

        // 2. 홈으로 이동
        return "redirect:/";
    }

    @GetMapping("")
    public String loginForm() {
        return "login/loginForm";
    }

    @PostMapping("")
    public String login(@ModelAttribute("userDto") UserDto userDto, boolean rememberId, String toURL, Model m,
                                                        HttpServletRequest request, HttpServletResponse response) {

        m.addAttribute("welcomeId", userDto.getUserId());

        System.out.println("userDto.getUserId() => " + userDto.getUserId());
        System.out.println("userDto.getUserPwd() => " + userDto.getUserPwd());
        System.out.println("rememberId => " + rememberId);

        // 1. 아이디 / 비밀번호가 일치하면, 세션 객체 얻어오기
        HttpSession session = request.getSession();

        // 2. 그리고 session에 id 를 저장 (BoardController.java 에서 해당 세션 이용함)
        session.setAttribute("id", userDto.getUserId());

        if(rememberId) {    // 3. 동시에, 아이디 기억하기(rememberId) 에 체크돼 있다면,
            // 쿠키 생성
            Cookie cookie = new Cookie("id", userDto.getUserId());

            // 응답에 쿠키를 추가해서 브라우저로 전송.
            response.addCookie(cookie);
        } else {            // 반면, 아이디 / 비밀번호는 일치하지만, 아이디 기억하기(rememberId) 에 체크가 "안" 돼 있다면
            // 쿠키 생성하기
            Cookie cookie = new Cookie("id", userDto.getUserId());

            // 쿠키 삭제 (유효 시간을 0, 혹시 있을 쿠키 삭제)
            cookie.setMaxAge(0);

            // 응답에 쿠키를 추가해서 브라우저로 전송
            response.addCookie(cookie);
        }

        // 4. 홈 또는 게시판(board/list) 으로 이동
        // 즉, loginForm.jsp 의   name="toURL" value="${param.toURL}" 에서 여기로 넘어온 값이 없으면 홈으로 이동
        toURL = (toURL == null || toURL.equals("") ? "/" : toURL);

        return "redirect:" + toURL;
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