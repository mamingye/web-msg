package com.weibo.message.service.impl;

import com.weibo.message.dao.UserMapper;
import com.weibo.message.domain.User;
import com.weibo.message.service.UserService;
import com.weibo.message.utils.Base.DataResult;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by REDUDPY on 2018/4/25.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userName, String password) {
        return userMapper.getUserByName(password);
    }

    @Override
    public DataResult getUserByUser(User user, HttpServletRequest request, HttpServletResponse response) {
        if (user==null || StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())){
            return DataResult.build(500,"empty");
        }
        User dto = userMapper.getUserByName(user.getUserName());
        if (dto==null){
            return DataResult.build(500,"empty");
        }
        if (!StringUtils.equals(user.getPassword(),dto.getPassword())){
            return DataResult.build(505,"error");
        }

        return DataResult.build(200,"ok");
    }

    @Override
    public User getUserByToken(String token) {
        return userMapper.getUserByName(token);

    }

	@Override
	public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, HttpServletResponse response){
		     try {
		       	 String path=request.getSession().getServletContext().getRealPath("/")+"WEB-INF/template/template.txt";
		         File file=new File(path);  
		         HttpHeaders headers = new HttpHeaders();    
		         String fileName=new String("模板.txt".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
		         headers.setContentDispositionFormData("attachment", fileName);   
		         headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
		         return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);    
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		    
	    }
}
