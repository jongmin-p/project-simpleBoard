package com.proj.travelog.dao;

import com.proj.travelog.domain.User;

public interface UserDao {
    User selectUser(String id) throws Exception;
    int deleteUser(String id) throws Exception;
    int insertUser(User user) throws Exception;
    int updateUser(User user) throws Exception;
    int count() throws Exception;               // ch4 시작할 때 추가된 메서드
    void deleteAll() throws Exception;
}