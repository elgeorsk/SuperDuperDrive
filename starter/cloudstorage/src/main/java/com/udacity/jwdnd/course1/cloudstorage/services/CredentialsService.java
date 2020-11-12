package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialsService {

    private CredentialsMapper credentialMapper;
    private EncryptionService encryptionService;
    private UsersMapper userMapper;

    public CredentialsService(CredentialsMapper credentialMapper, UsersMapper userMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
        this.userMapper = userMapper;
    }

    public String salt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public int createNewCredential(String username, Credentials credential) {
        String encodedSalt = salt();
        credential.setUserId(userMapper.getUserId(username));
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedSalt);
        return credentialMapper.insertCredential(new Credentials(null, credential.getUrl(), credential.getUsername(), encodedSalt, encryptedPassword, credential.getUserId()));
    }

    public String decryptPassword(Credentials credential) {
        return encryptionService.decryptValue(credential.getPassword(), credential.getKey());
    }

    public int updateCredential(Credentials credential){
        String encodedSalt = salt();
        credential.setKey(encodedSalt);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), encodedSalt));
        return credentialMapper.updateCredential(credential);
    }

    public void deleteCredential(Long credentialId){
        credentialMapper.deleteCredentialById(credentialId);
    }


    public List<Credentials> getAllCredentials(String username) {
        return credentialMapper.getAllCredentials(userMapper.getUserId(username));
    }
}
