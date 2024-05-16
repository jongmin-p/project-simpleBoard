package com.jmpark.simpleBoard.dao;

import com.jmpark.simpleBoard.domain.UserDto;

public interface UserDao {
    int insert(UserDto userDto) throws Exception;
    UserDto select(String userId) throws Exception;
}
