package com.jmpark.simpleBoard.dao;

import com.jmpark.simpleBoard.domain.BoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)     // 1. ApplicationContext ac     를 자동으로 만들어 주고,
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})  // 2. 설정해 줌 (root-context.xml 에 빈 등록하기)
public class BoardDaoImplTest {

    @Autowired BoardDao boardDao;

    @Test
    public void selectTest() throws Exception {
        // assertTrue(boardDao != null);
        // System.out.println("boardDao = " + boardDao);

        BoardDto boardDto = boardDao.select(1);
        System.out.println("\nboardDto => " + boardDto);

        assertTrue(boardDto.getBoardNo() == 1);
    }
}