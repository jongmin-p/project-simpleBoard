package com.jmpark.simpleBoard.dao;

import com.jmpark.simpleBoard.domain.CommentDto;

import java.util.List;

public interface CommentDao {
    int insert(CommentDto dto) throws Exception;

    CommentDto select(Integer commentNo) throws Exception;

    List<CommentDto> selectAll(Integer boardNo) throws Exception;

    int update(CommentDto dto) throws Exception;

    int delete(Integer commentNo, String writer) throws Exception;

    int deleteAll(Integer boardNo) throws Exception;

    int count(Integer boardNo) throws Exception;
}
