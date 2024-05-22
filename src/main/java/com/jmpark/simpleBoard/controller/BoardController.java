package com.jmpark.simpleBoard.controller;

import com.jmpark.simpleBoard.domain.BoardDto;
import com.jmpark.simpleBoard.domain.PageHandler;
import com.jmpark.simpleBoard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired BoardService boardService;


    // 게시글 삭제 메서드
    @PostMapping("/remove")
    public String remove(Integer boardNo, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        // writer 는 세션에서 가져와야 함. 해당 글 작성자만 그 게시글을 삭제할 수 있어야 하기 때문.
        String writer = (String)session.getAttribute("id");

        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            int rowCnt = boardService.remove(boardNo, writer);

            // 삭제 도중 에러가 발생한다면 catch 문으로 가서 처리
            if(rowCnt != 1) {
                throw new Exception("Board Remove Error!!!");
            }

            // 잘 삭제가 됐다면, 삭제 성공 메시지 띄우고 다시 게시글 목록으로 돌아가기
            rattr.addFlashAttribute("msg", "DEL_OK");
        } catch (Exception e) {
            rattr.addFlashAttribute("msg", "DEL_ERR");
        }

        // 삭제된 이후에는 /board/list 로 가야 함
        return "redirect:/board/list";
    }


    @GetMapping("/read")
    public String read(Integer boardNo, Integer page, Integer pageSize, HttpSession session, Model m) {
        try {
            // 지금 현재 로그인한 유저가 누군지 구분하기 위해서 (수정/삭제 버튼 출력 유무)
            String currentUser = (String)session.getAttribute("id");

            BoardDto boardDto = boardService.read(boardNo);

            m.addAttribute("boardDto", boardDto);

            // board.jsp 페이지(작성글 읽기 페이지) 에서 목록 버튼 눌렀을 때, 기존 게시판 페이지로 이동하기 위함
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            m.addAttribute("currentUser", currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "board/board";
    }

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