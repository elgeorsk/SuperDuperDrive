package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private FilesMapper filesMapper;
    private UsersMapper userMapper;

    public FileService(FilesMapper filesMapper, UsersMapper userMapper) {
        this.filesMapper = filesMapper;
        this.userMapper = userMapper;
    }

    public List<Files> getAllFiles(String username) {
        return filesMapper.getAllFiles(userMapper.getUserId(username));
    }
}


