package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {
    @Insert("INSERT INTO FILES( filename, contentType, fileSize, fileData, userId) " +
            "VALUES (#{filename}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(Files file);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId} and userId = #{userId}")
    Files getFileByFileId(Long fileId, Long userId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    Files getFileByUserId(Long userId);

    @Select("SELECT * FROM FILES where userId = #{userId}")
    List<Files> getAllFiles(Long userId);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void delete(Long fileId);
}
