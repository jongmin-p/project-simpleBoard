package com.jmpark.simpleBoard.service;

import com.jmpark.simpleBoard.domain.UserDto;

public interface UserService {
    int write(UserDto userDto) throws Exception;
    String read(String userId) throws Exception;
}
