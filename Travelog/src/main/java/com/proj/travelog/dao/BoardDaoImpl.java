package com.proj.travelog.dao;

import com.proj.travelog.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository     // bean 등록 (이거 안하면 Test 에서 BoardDao 주입 안 됨)
public class BoardDaoImpl implements BoardDao {

    // session 의 메서드를 이용하기 위해 주입
    @Autowired
    SqlSession session;

    // boardMapper 에서 사용한 namespace 를 써줘야 함. (끝에 . 점 붙이기)
    String namespace = "com.proj.travelog.dao.BoardMapper.";


    // 만약 예외가 발생하면 Service 계층으로 던지기 위해 throws Exception
    @Override
    public BoardDto select(int bno) throws Exception {
        return session.selectOne(namespace + "select", bno);
    }

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public int insert(BoardDto dto) throws Exception {
        return session.insert(namespace + "insert", dto);
    } // int insert(String statement, Object parameter)

    @Override
    public int update(BoardDto dto) throws Exception {
        return session.update(namespace + "update", dto);
    } // int update(String statement, Object parameter)

    @Override
    public int delete(Integer bno, String writer) throws Exception {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", writer);
        return session.delete(namespace + "delete", map);
    } // int delete(String statement, Object parameter)

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    } // List<E> selectList(String statement)

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace + "increaseViewCnt", bno);
    } // int update(String statement, Object parameter)

    @Override
    public int deleteAll() {
        return session.delete(namespace + "deleteAll");
    } // int delete(String statement)
}