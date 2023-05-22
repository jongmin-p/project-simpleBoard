package com.proj.travelog.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {
    @Test
    public void test() {
        PageHandler ph = new PageHandler(250, 1);

        ph.print();
        System.out.println("ph = " + ph);

        assertTrue(ph.getBeginPage() == 1);
        assertTrue(ph.getEndPage() == 10);
    }


    @Test
    public void test222() {
        PageHandler ph = new PageHandler(250, 11);

        ph.print();
        System.out.println("ph = " + ph);

        // 검증 조건 바꿔주기
        assertTrue(ph.getBeginPage() == 11);
        assertTrue(ph.getEndPage() == 20);
    }


    @Test
    public void test333() {
        PageHandler ph = new PageHandler(255, 25);

        ph.print();
        System.out.println("ph = " + ph);

        // 검증 조건 바꿔주기
        assertTrue(ph.getBeginPage() == 21);
        assertTrue(ph.getEndPage() == 26);
    }

    @Test
    public void test444() {
        PageHandler ph = new PageHandler(255, 10);

        ph.print();
        System.out.println("ph = " + ph);

        // 검증 조건 바꿔주기
        assertTrue(ph.getBeginPage() == 1);
        assertTrue(ph.getEndPage() == 10);
    }
}