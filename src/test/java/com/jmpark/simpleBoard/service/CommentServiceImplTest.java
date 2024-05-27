package com.jmpark.simpleBoard.service;

import com.jmpark.simpleBoard.dao.BoardDao;
import com.jmpark.simpleBoard.dao.CommentDao;
import com.jmpark.simpleBoard.domain.BoardDto;
import com.jmpark.simpleBoard.domain.CommentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommentServiceImplTest {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentDao commentDao;

    @Autowired
    BoardDao boardDao;


    @Test
    public void write() throws Exception {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("hello", "hello", "asdf");
        assertTrue(boardDao.insert(boardDto) == 1);

        Integer boardNo = boardDao.selectAll().get(0).getBoardNo();
        System.out.println("boardNo = " + boardNo);

        commentDao.deleteAll(boardNo);

        CommentDto commentDto = new CommentDto(boardNo,0,"hi","qwer");
        assertTrue(boardDao.select(boardNo).getCommentCnt() == 0);
        assertTrue(commentService.write(commentDto) == 1);

        Integer commentNo = commentDao.selectAll(boardNo).get(0).getCommentNo();
        assertTrue(boardDao.select(boardNo).getCommentCnt() == 1);
    }

    @Test
    public void read() throws Exception {
    }

    @Test
    public void getList() throws Exception {
    }

    @Test
    public void modify() throws Exception {
    }

    @Test
    public void remove() throws Exception {
        boardDao.deleteAll();

        BoardDto boardDto = new BoardDto("hello", "hello", "asdf");
        assertTrue(boardDao.insert(boardDto) == 1);

        Integer boardNo = boardDao.selectAll().get(0).getBoardNo();
        System.out.println("boardNo = " + boardNo);

        commentDao.deleteAll(boardNo);

        CommentDto commentDto = new CommentDto(boardNo,0,"hi","qwer");
        assertTrue(boardDao.select(boardNo).getCommentCnt() == 0);
        assertTrue(commentService.write(commentDto) == 1);
        assertTrue(boardDao.select(boardNo).getCommentCnt() == 1);

        Integer commentNo = commentDao.selectAll(boardNo).get(0).getCommentNo();

        // 일부러 예외를 발생시키고 Tx가 취소되는지 확인해야.
        int rowCnt = commentService.remove(commentNo, boardNo, commentDto.getWriter());
        assertTrue(rowCnt == 1);
        assertTrue(boardDao.select(boardNo).getCommentCnt() == 0);
    }

    @Test
    public void getCount() {
    }
}