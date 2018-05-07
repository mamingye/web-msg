package com.weibo.message.controller;

import com.weibo.message.domain.User;
import com.weibo.message.service.UserService;
import com.weibo.message.utils.Base.DataResult;
import com.weibo.message.utils.Base.WordDefined;
import com.weibo.message.utils.CookieUtils;
import com.weibo.message.utils.ReadUtil;
import com.weibo.message.utils.SendMessageUtil;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by REDUDPY on 2018/4/25.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
   @Autowired
   private UserService userService;
   @RequestMapping(value = "/login", method = RequestMethod.GET)
   public String login() {
      return "login";
   }

   @RequestMapping(value = "/login", method = RequestMethod.POST)
   public String login(String userName, String password, HttpSession session, RedirectAttributes attributes,
                       WordDefined defined,HttpServletRequest request,HttpServletResponse response) {
      User user = userService.login(userName,password);
      if (user == null) {
         attributes.addFlashAttribute("error", defined.LOGIN_USERID_ERROR);
         return "redirect:/user/login";
      }
         if (!user.getPassword().equals(password)) {
            attributes.addFlashAttribute("error", defined.LOGIN_PASSWORD_ERROR);
            return "redirect:/user/login";
         }
      CookieUtils.setCookie(request, response,"TT_TOKEN",userName);
       return "redirect:/user/index";

   }

   @RequestMapping(value = "/logout")
   public String logout(HttpSession session, RedirectAttributes attributes, WordDefined defined,HttpServletRequest request,HttpServletResponse response) {
      CookieUtils.deleteCookie(request,response,"TT_TOKEN");
      attributes.addFlashAttribute("message", defined.LOGOUT_SUCCESS);
      return "redirect:/user/login";
   }
   @RequestMapping(value = "/index")
   public String index() {
      return "index";
   }
   @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
   public String sendMessage() {
      return "index";
   }
   @RequestMapping(value = "/sendMessage")
   @ResponseBody
   public DataResult sendMessage(String account,String password,String message,MultipartFile receive,Model model,HttpServletRequest request,HttpServletResponse response){
	   
	  if(receive == null) {
		   return DataResult.build(404, "上传文件不能为空！");
	  }
      List<String> listReceive = ReadUtil.readFile(receive);
      DataResult result = SendMessageUtil.sendMessage(account, password, message, listReceive);
      model.addAttribute("account", account);
      model.addAttribute("password", password);
      model.addAttribute("message", message);
      return result;
   }
   @RequestMapping(value="/downloadTemplate")
   public ResponseEntity<byte[]>downloadTemplate(HttpServletRequest request,HttpServletResponse response){
	  return userService.downloadFile(request, response);
   }
}
