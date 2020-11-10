package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Users;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    Users findUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) " +
            "VALUES(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(Users user);

    @Delete("DELETE FROM USERS WHERE userId = #{userId}")
    void deleteUser(Integer userId);
}
