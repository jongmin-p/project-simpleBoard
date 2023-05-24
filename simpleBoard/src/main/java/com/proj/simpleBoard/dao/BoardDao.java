package com.proj.simpleBoard.dao;

import com.proj.simpleBoard.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    // 만약 예외가 발생하면 Service 계층으로 던지기 위해 throws Exception
    BoardDto select(int bno) throws Exception;
    int count() throws Exception;
    int insert(BoardDto dto) throws Exception;
    int update(BoardDto dto) throws Exception;
    int delete(Integer bno, String writer) throws Exception;
    List<BoardDto> selectAll() throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;
    int deleteAll();

    List<BoardDto> selectPage(Map map) throws Exception;
}