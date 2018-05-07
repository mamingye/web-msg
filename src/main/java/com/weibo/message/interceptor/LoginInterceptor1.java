package com.weibo.message.interceptor;

import com.weibo.message.domain.User;
import com.weibo.message.service.impl.UserServiceImpl;
import com.weibo.message.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * NAME   :  EmailSystem/com.amayadream.interceptor
 * Author :  Amayadream
 * Date   :  2015.10.06 17:33
 * TODO   :
 */
public class LoginInterceptor1 extends HandlerInterceptorAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        User user = userService.getUserByToken(token);
        if (user==null){
            //跳转到登录页面，把用户请求的url作为参数传递给登录页面。
            response.sendRedirect("/login.jsp");
            return false;
        }
        request.setAttribute("user",user);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //handler执行之后，返回modelAndView之前执行
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //返回modelAndView之后执行
    }


}
