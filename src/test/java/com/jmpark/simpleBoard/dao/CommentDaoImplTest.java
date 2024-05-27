package com.jmpark.simpleBoard.dao;

import com.jmpark.simpleBoard.domain.CommentDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommentDaoImplTest {

    @Autowired
    CommentDao commentDao;


    @Test
    public void insert() throws Exception {
        commentDao.deleteAll(1);

        CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf");

        assertTrue(commentDao.insert(commentDto) == 1);
        assertTrue(commentDao.count(1)  == 1);

        commentDto = new CommentDto(1, 0, "comment", "asdf");

        assertTrue(commentDao.insert(commentDto) == 1);
        assertTrue(commentDao.count(1)  == 2);
    }

    @Test
    public void select() throws Exception {
        commentDao.deleteAll(1);

        CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf");

        assertTrue(commentDao.insert(commentDto) == 1);
        assertTrue(commentDao.count(1)  == 1);

        List<CommentDto> list = commentDao.selectAll(1);

        String comment = list.get(0).getComment();
        String writer = list.get(0).getWriter();

        assertTrue(comment.equals(commentDto.getComment()));
        assertTrue(writer.equals(commentDto.getWriter()));
    }

    @Test
    public void selectAll() throws Exception {
        commentDao.deleteAll(1);

        CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf");

        assertTrue(commentDao.insert(commentDto) == 1);
        assertTrue(commentDao.count(1)  == 1);

        List<CommentDto> list = commentDao.selectAll(1);

        assertTrue(list.size() == 1);

        commentDto = new CommentDto(1, 0, "comment", "asdf");

        assertTrue(commentDao.insert(commentDto) == 1);
        assertTrue(commentDao.count(1)  == 2);

        list = commentDao.selectAll(1);

        assertTrue(list.size() == 2);
    }

    @Test
    public void update() throws Exception {
        commentDao.deleteAll(1);

        CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf");

        assertTrue(commentDao.insert(commentDto) == 1);
        assertTrue(commentDao.count(1)  == 1);

        List<CommentDto> list = commentDao.selectAll(1);

        commentDto.setCommentNo(list.get(0).getCommentNo());
        commentDto.setComment("comment2");

        assertTrue(commentDao.update(commentDto) == 1);

        list = commentDao.selectAll(1);

        String comment = list.get(0).getComment();
        String writer = list.get(0).getWriter();

        assertTrue(comment.equals(commentDto.getComment()));
        assertTrue(writer.equals(commentDto.getWriter()));
    }

    @Test
    public void delete() throws Exception {
        commentDao.deleteAll(1);

        CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf");

        assertTrue(commentDao.insert(commentDto) == 1);
        assertTrue(commentDao.count(1)  == 1);
    }

    @Test
    public void count() throws Exception {
        commentDao.deleteAll(1);

        assertTrue(commentDao.count(1) == 0);
    }
}