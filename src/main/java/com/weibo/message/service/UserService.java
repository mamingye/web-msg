package com.weibo.message.service;

import com.weibo.message.domain.User;
import com.weibo.message.utils.Base.DataResult;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Created by REDUDPY on 2018/4/25.
 */
public interface UserService {
    User login(String userName,String password);
    DataResult getUserByUser(User user, HttpServletRequest request, HttpServletResponse response);
    User getUserByToken(String token);
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, HttpServletResponse response);
}
