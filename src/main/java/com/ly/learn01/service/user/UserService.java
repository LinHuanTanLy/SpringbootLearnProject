package com.ly.learn01.service.user;

import com.ly.learn01.domain.dao.user.User;
import com.ly.learn01.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


  public   User findOneByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }
}
