package com.jmpark.simpleBoard.dao;

import com.jmpark.simpleBoard.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired SqlSession session;

    String namespace = "com.jmpark.simpleBoard.dao.BoardMapper.";


    // 특정 게시글 하나 조회
    @Override
    public BoardDto select(Integer boardNo) throws Exception {
        return session.selectOne(namespace + "select", boardNo);
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    // 페이징 처리
    @Override
    public List<BoardDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace + "selectPage", map);
    } // List<E> selectList(String statement, Object parameter)

    // 전체 게시글 수
    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public int increaseViewCnt(Integer boardNo) throws Exception {
        return session.update(namespace + "increaseViewCnt", boardNo);
    }
}