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

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    Files getFileById(Long fileId);

    @Select("SELECT fileId,filename FROM FILES where userId = #{userId}")
    List<Files> getAllFiles(Long userId);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void delete(Long fileId);
}
