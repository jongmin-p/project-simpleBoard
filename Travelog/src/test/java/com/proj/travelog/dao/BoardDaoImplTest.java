package com.proj.travelog.dao;

import com.proj.travelog.domain.BoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoImplTest {

    @Autowired
    BoardDao boardDao;


    @Test       // 예외 던질 수도 있으니 throws Exception
    public void select() throws Exception {
        // 먼저, boardDao 가 주입이 잘 됐는지 확인하자. (즉, boardDao 가 null 이 아닌지 확인)
        // assertTrue(boardDao != null);    // 통과
        // System.out.println("boardDao = " + boardDao);

        BoardDto boardDto = boardDao.select(1);
        System.out.println("boardDto = " + boardDto);
        assertTrue(boardDto.getBno().equals(1));
    }

    @Test
    public void count() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void increaseViewCnt() {
    }

    @Test
    public void deleteAll() {
    }
}