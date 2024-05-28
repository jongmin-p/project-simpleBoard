package com.jmpark.simpleBoard.controller;

import com.jmpark.simpleBoard.domain.CommentDto;
import com.jmpark.simpleBoard.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

// @Controller
// @ResponseBody
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;


    // 3. 댓글을 저장하는 메서드
    @PostMapping("/comments")    //    /simpleBoard/comments?boardNo=1085     (POST)
    public ResponseEntity<String> write(@RequestBody CommentDto commentDto, Integer boardNo, HttpSession session) {

        // getAttribute 는 Object 반환하기에, 형변환 필요
        String writer = (String)session.getAttribute("id");

        commentDto.setWriter(writer);
        commentDto.setBoardNo(boardNo);
        System.out.println("commentDto = " + commentDto);

        try {
            int rowCnt = commentService.write(commentDto);

            if(rowCnt != 1) {
                throw new Exception("Write Failed..");
            }

            return new ResponseEntity<String>("WRT_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }


    // 1. 지정된 게시물의 모든 댓글들을 가져오는 메서드
    @RequestMapping("/comments")        //  /comments?boardNo=1080  (GET)
    public ResponseEntity<List<CommentDto>> list(Integer boardNo) {
        List<CommentDto> list = null;

        try {
            list = commentService.getList(boardNo);

            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);       // 상태코드 200
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);    // 상태코드 400
        }
    }


    // 4. 댓글을 수정하는 메서드
    @PatchMapping("/comments/{commentNo}")    //    /simpleBoard/comments/31     (PATCH)
    public ResponseEntity<String> modify(@PathVariable Integer commentNo, @RequestBody CommentDto commentDto, HttpSession session) {

        // getAttribute 는 Object 반환하기에, 형변환 필요
        String writer = (String)session.getAttribute("id");

        commentDto.setWriter(writer);
        commentDto.setCommentNo(commentNo);
        System.out.println("commentDto = " + commentDto);

        try {
            int rowCnt = commentService.modify(commentDto);

            if(rowCnt != 1) {
                throw new Exception("Modify Failed..");
            }

            return new ResponseEntity<String>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }


    // 2. 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{commentNo}")    // 삭제    DeleteMapping         ( /comments/100 )
    public ResponseEntity<String> remove(@PathVariable Integer commentNo, Integer boardNo, HttpSession session) {

        // getAttribute 는 Object 반환하기에, 형변환 필요
        String writer = (String)session.getAttribute("id");

        try {
            int rowCnt = commentService.remove(commentNo, boardNo, writer);

            if(rowCnt != 1) {
                throw new Exception("Delete Failed..");
            }

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }
}