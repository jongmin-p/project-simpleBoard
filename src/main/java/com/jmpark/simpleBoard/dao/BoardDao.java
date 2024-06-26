package com.jmpark.simpleBoard.dao;

import com.jmpark.simpleBoard.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardDao {

    int insert(BoardDto boardDto) throws Exception;

    // 특정 게시글 하나 조회
    BoardDto select(Integer boardNo) throws Exception;

    int update(BoardDto boardDto) throws Exception;

    // 게시글 삭제
    int delete(Integer boardNo, String writer) throws Exception;

    // 테이블의 모든 데이터 삭제
    int deleteAll() throws Exception;

    List<BoardDto> selectAll() throws Exception;

    // 페이징 처리
    List<BoardDto> selectPage(Map map) throws Exception; // List<E> selectList(String statement, Object parameter)

    // 전체 게시글 수
    int count() throws Exception;

    int increaseViewCnt(Integer boardNo) throws Exception;

    int updateCommentCnt(Integer boardNo, int cnt);
}
