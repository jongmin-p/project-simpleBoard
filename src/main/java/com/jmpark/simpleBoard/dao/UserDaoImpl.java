package com.jmpark.simpleBoard.dao;

import com.jmpark.simpleBoard.domain.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SqlSession session;

    String namespace = "com.jmpark.simpleBoard.dao.UserMapper.";

    @Override
    public int insert(UserDto userDto) throws Exception {
        return session.insert(namespace + "insert", userDto);
    }

    @Override
    public UserDto select(String userId) throws Exception {
        return session.selectOne(namespace + "select", userId);
    }
}