package com.jmpark.simpleBoard.dao;

import com.jmpark.simpleBoard.domain.CommentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    SqlSession session;

    String namespace = "com.jmpark.simpleBoard.dao.CommentMapper.";


    @Override
    public int insert(CommentDto dto) throws Exception {
        return session.insert(namespace + "insert", dto);
    } // int insert(String statement, Object parameter)

    @Override
    public CommentDto select(Integer commentNo) throws Exception {
        return session.selectOne(namespace + "select", commentNo);
    } // T selectOne(String statement, Object parameter)

    @Override
    public List<CommentDto> selectAll(Integer boardNo) throws Exception {
        return session.selectList(namespace + "selectAll", boardNo);
    } // List<E> selectList(String statement)

    @Override
    public int update(CommentDto dto) throws Exception {
        return session.update(namespace + "update", dto);
    } // int update(String statement, Object parameter)

    @Override
    public int delete(Integer commentNo, String writer) throws Exception {
        Map map = new HashMap();
        map.put("commentNo", commentNo);
        map.put("writer", writer);
        return session.delete(namespace + "delete", map);
    } // int delete(String statement, Object parameter)

    @Override
    public int deleteAll(Integer boardNo) throws Exception {
        // 몇 개 지워졌는지 반환
        return session.delete(namespace + "deleteAll", boardNo);
    }

    @Override
    public int count(Integer boardNo) throws Exception {
        return session.selectOne(namespace + "count", boardNo);
    } // T selectOne(String statement)
}