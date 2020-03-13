package com.ly.learn01.mapper;

import com.ly.learn01.domain.dao.user.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {


    @Select("SELECT * FROM USER WHERE username = #{userName}")
    public User getUserByUserName(String userName);
}
