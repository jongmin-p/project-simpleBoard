package com.proj.travelog.dao;

import com.proj.travelog.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    // 만약 예외가 발생하면 Service 계층으로 던지기 위해 throws Exception
    BoardDto select(int bno) throws Exception;
    int count() throws Exception;
    int insert(BoardDto dto) throws Exception;                  // int insert(String statement, Object parameter)
    int update(BoardDto dto) throws Exception;                  // int update(String statement, Object parameter)
    int delete(Integer bno, String writer) throws Exception;    // int delete(String statement, Object parameter)
    List<BoardDto> selectAll() throws Exception;                // List<E> selectList(String statement)
    int increaseViewCnt(Integer bno) throws Exception;          // int update(String statement, Object parameter)
    int deleteAll();                                            // int delete(String statement)


    // 이거 BoardDaoImplTest 에서 에러뜨는데 강의에서는 일단 지나감 (무시)
    List<BoardDto> selectPage(Map map) throws Exception;

    // 이건 아직 강의에 나오지 않는 내용
//    int searchResultCnt(SearchCondition sc) throws Exception;
//    List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;
}