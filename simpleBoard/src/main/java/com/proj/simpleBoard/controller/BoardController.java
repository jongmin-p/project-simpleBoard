package com.proj.simpleBoard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.proj.simpleBoard.domain.BoardDto;
import com.proj.simpleBoard.domain.PageHandler;
import com.proj.simpleBoard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	@PostMapping("modify")
	public String modify(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {

		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);		// 글쓴 사람 아이디로 집어넣기

		try {
			int rowCnt = boardService.modify(boardDto);

			if(rowCnt != 1) {
				// INSERT 가 되지 않으면, 예외를 던져서, 게시판으로 이동하지 않고, 입력했던 내용을 다시 보여주도록
				throw new Exception("Modify Failed");
			}

			// 처리가 잘 됐으면, 성공 메시지
			rattr.addFlashAttribute("msg", "MOD_OK");

			// DB 등록 후, 페이지 이동
			return "redirect:/board/list";

		} catch (Exception e) {
			e.printStackTrace();

			// 에러가 발생하면, 입력했던 내용을 그대로 보여주도록 (입력한 내용 잃어버리지 않게)
			m.addAttribute("boardDto", boardDto);
			rattr.addFlashAttribute("msg", "MOD_ERR");		// 에러 메시지

			// 도중에 에러가 나면, 다시 글쓰기 화면으로
			return "/board/board";
		}
	}
	

	// 작성글 처리
	@PostMapping("write")
	public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {

		// 작성자 알아야 하니까 Session 에서 가져오기
		String writer = (String)session.getAttribute("id");
		boardDto.setWriter(writer);		// 글쓴 사람 아이디로 집어넣기

		try {
			int rowCnt = boardService.write(boardDto);

			if(rowCnt != 1) {
				// INSERT 가 되지 않으면, 예외를 던져서, 게시판으로 이동하지 않고, 입력했던 내용을 다시 보여주도록
				throw new Exception("Write Failed");
			}

			// 처리가 잘 됐으면, 성공 메시지
			rattr.addFlashAttribute("msg", "WRT_OK");

			// DB 등록 후, 페이지 이동
			return "redirect:/board/list";		// 최신글이니까  페이지 상관없이 그냥 list 로 보내면 됨

		} catch (Exception e) {
			e.printStackTrace();

			// 에러가 발생하면, 입력했던 내용을 그대로 보여주도록 (입력한 내용 잃어버리지 않게)
			m.addAttribute("boardDto", boardDto);
			rattr.addFlashAttribute("msg", "WRT_ERR");		// 에러 메시지
			
			// 도중에 에러가 나면, 다시 글쓰기 화면으로
			return "/board/board";
		}
	}

	@GetMapping("/write")	// 이건 그냥 작성 폼 보여주는 화면
	public String write(Model m) {
		m.addAttribute("mode", "new");

		return "board/board";
	}

	@PostMapping("/remove")								// 작성자 id 가져와야해서 session, 메세지 한 번 만 뜨도록 하는 것이 RedirectAttributes
	public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {

		String writer = (String)session.getAttribute("id");

		try {
			int rowCnt = boardService.remove(bno, writer);

			// 삭제가 제대로 되지 않았을 경우 catch 로 이동
			if(rowCnt != 1) {
				throw new Exception("Removing board error");
			}

			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);

			// 삭제 이후 메시지 (boardList.jsp 에서 받음)
			rattr.addFlashAttribute("msg", "DEL_OK");
		} catch (Exception e) {
			e.printStackTrace();
			// 예외 발생 시
			rattr.addFlashAttribute("msg", "DEL_ERR");

		}

		// 삭제 후에는 다시 목록의 해당 페이지로 돌아감
		return "redirect:/board/list?page=" + page + "&pageSize=" + pageSize;
	}

	// 게시글 상세히 읽기?
	@GetMapping("/read")
	public String read(Integer bno, Integer page, Integer pageSize, HttpSession session, Model m) {
		try {
			// 지금 현재 로그인한 유저가 누군지 구분하기 위해서 (수정/삭제 버튼 출력 유무)
			String currentUser = (String)session.getAttribute("id");

			// Service에서 읽어온 것을 Dto 로 받기
			BoardDto boardDto = boardService.read(bno);

			m.addAttribute(boardDto);

			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);

			// 지금 현재 로그인한 유저가 누군지 구분하기 위해서 (수정/삭제 버튼 출력 유무)
			m.addAttribute("currentUser", currentUser);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "board/board";
	}

	@GetMapping("/list")
	public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
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

				m.addAttribute("page", page);
				m.addAttribute("pageSize", pageSize);

			} catch (Exception e) {
				e.printStackTrace();
			}

		return "board/boardList";
	}


	// 로그인 체크 하는 방법?
	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션을 얻어서
		HttpSession session = request.getSession();

		// 세션에 id 가 있으면 true 를, 없으면 false 를 반환  (로그인 시, LoginController 에서 세션을 저장해야 함)
		return session.getAttribute("id") != null;
	}


//	@GetMapping("/findIdForm")
//	public String findIdForm() {
//		return "login/findIdForm";
//	}
//
//	@GetMapping("/findPwdForm")
//	public String findPwdForm() {
//		return "login/findPwdForm";
//	}
}
