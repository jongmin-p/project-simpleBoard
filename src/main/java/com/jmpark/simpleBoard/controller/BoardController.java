package com.jmpark.simpleBoard.controller;

import com.jmpark.simpleBoard.domain.BoardDto;
import com.jmpark.simpleBoard.domain.PageHandler;
import com.jmpark.simpleBoard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired BoardService boardService;


    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {

        // 유저가 "Board" 탭을 눌렀을 때,
        // 로그인 체크한 뒤    만약 로그인 안 했다면, /login 페이지로 이동
        if(!loginCheck(request)) {
            return "redirect:/login?toURL=" + request.getRequestURL();
        }


        // 페이징 코드 추가
        if(page == null) {
            page = 1;
        }

        if(pageSize == null) {
            pageSize = 10;
        }

        // 페이징 코드 추가
        try {
            // 페이지 네비게이션
            int totalCnt = boardService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

            Map map = new HashMap();

            // boardMapper.xml 에서   select id="selectPage"  참고 (여기서 Map 으로 넘겨줌)
            map.put("offset", (page - 1) * pageSize);
            map.put("pageSize", pageSize);

            List<BoardDto> list = boardService.getPage(map);

            // 이제 list 를 모델에 담아서 뷰로 전송
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 로그인 했다면, 게시판 화면으로 이동
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