package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    private FilesMapper filesMapper;
    private UsersMapper userMapper;

    public FileService(FilesMapper filesMapper, UsersMapper userMapper) {
        this.filesMapper = filesMapper;
        this.userMapper = userMapper;
    }

    public boolean isFilenameAvailable(String username) {
        return filesMapper.getFileByUserId(userMapper.getUserId(username)) == null;
    }

    public int createNewFile(String username, MultipartFile uploadFile){
        Long size = uploadFile.getSize();
        Files file = new Files();
        try {

            file.setFilename(uploadFile.getOriginalFilename());
            file.setContentType(uploadFile.getContentType());
            file.setFileSize(size.toString());
            file.setFileData(uploadFile.getBytes());
            file.setUserId(userMapper.getUserId(username));

            return filesMapper.insertFile(file);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public Files getFileByUserId(String username) {
        return filesMapper.getFileByUserId(userMapper.getUserId(username));}

    public void deleteFile(Long fileId){
        filesMapper.delete(fileId);
    }

    public List<Files> getAllFiles(String username) {
        return filesMapper.getAllFiles(userMapper.getUserId(username));
    }
}


