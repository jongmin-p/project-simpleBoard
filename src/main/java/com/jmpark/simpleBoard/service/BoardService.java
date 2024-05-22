package com.jmpark.simpleBoard.service;

import com.jmpark.simpleBoard.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {
    BoardDto read(Integer boardNo) throws Exception;

    List<BoardDto> getList() throws Exception;

    int remove(Integer boardNo, String writer) throws Exception;

    int getCount() throws Exception;

    // 페이징 처리 메서드
    List<BoardDto> getPage(Map map) throws Exception;
}
