package com.jmpark.simpleBoard.service;

import com.jmpark.simpleBoard.dao.UserDao;
import com.jmpark.simpleBoard.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

       @Autowired
       UserDao userDao;

       @Override
       public int write(UserDto userDto) throws Exception {
              return userDao.insert(userDto);
       }

       @Override
       public String read(String userId) throws Exception {
              return userDao.select(userId);
       }
}