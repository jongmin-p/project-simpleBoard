package com.jmpark.simpleBoard.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {

    @Test
    public void test() {
        PageHandler ph = new PageHandler(250, 1);      // 총 게시글 수 250개, 현재 페이지가 1 이라고 가정
        ph.print();                                                 // 실제 페이지 네비게이션 출력

        assertTrue(ph.getBeginPage() == 1);                // 내비게이션의 첫번째 페이지    (성공)
        assertTrue(ph.getEndPage()   == 10);               // 내비게이션의 마지막 페이지    (성공)
    }


    @Test
    public void test222() {
        PageHandler ph = new PageHandler(250, 11);     // 총 게시글 수 250개, 현재 페이지가 11 이라고 가정
        ph.print();                                                 // 실제 페이지 네비게이션 출력

        assertTrue(ph.getBeginPage() == 11);               // 내비게이션의 첫번째 페이지    (성공)
        assertTrue(ph.getEndPage()   == 20);               // 내비게이션의 마지막 페이지    (성공)
    }


    @Test
    public void test333() {
        PageHandler ph = new PageHandler(255, 25);     // 총 게시글 수 255개, 현재 페이지가 25 라고 가정
        ph.print();                                                 // 실제 페이지 네비게이션 출력

        assertTrue(ph.getBeginPage() == 21);               // 내비게이션의 첫번째 페이지    (성공)
        assertTrue(ph.getEndPage()   == 26);               // 내비게이션의 마지막 페이지    (성공)
    }


    @Test
    public void test444() {
        PageHandler ph = new PageHandler(255, 10);     // 총 게시글 수 255개, 현재 페이지가 10 이라고 가정
        ph.print();                                                 // 실제 페이지 네비게이션 출력

        assertTrue(ph.getBeginPage() == 1);                // 내비게이션의 첫번째 페이지가 1  이어야 함    (성공)
        assertTrue(ph.getEndPage()   == 10);               // 내비게이션의 마지막 페이지가 10 이어야 함    (성공)
    }
}