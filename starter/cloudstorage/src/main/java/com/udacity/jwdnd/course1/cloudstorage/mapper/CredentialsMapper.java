package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS where userId = #{userId}")
    List<Credentials> getAllCredentials(Long userId);

    @Insert("INSERT INTO CREDENTIALS(url, username, key, password, userId) VALUES (" +
            "#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credentials credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    void deleteCredentialById(Long credentialId);


    @Update("UPDATE CREDENTIALS " +
            "SET url = #{url}, key = #{key}, password = #{password} WHERE credentialId = #{credentialId}")
    int updateCredential(Credentials credential);

}
