package com.weibo.message.interceptor;

import com.weibo.message.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * NAME   :  EmailSystem/com.amayadream.interceptor
 * Author :  Amayadream
 * Date   :  2015.10.06 17:33
 * TODO   :
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private List<String> IGNORE_URI;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取URI后缀
        String requestUri = request.getServletPath();

        if(requestUri.equalsIgnoreCase("/")){
            response.sendRedirect("/user/login");
            return false;
        }

        //过滤不需要拦截的地址
        for (String uri : IGNORE_URI) {
            if (requestUri.startsWith(uri)) {
                return true;
            }
        }
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        if(StringUtils.isBlank(token)){
            response.sendRedirect("/user/login");
            return false;
        }
        //response.sendRedirect("/user/login");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    public List<String> getIGNORE_URI() {
        return IGNORE_URI;
    }

    public void setIGNORE_URI(List<String> IGNORE_URI) {
        this.IGNORE_URI = IGNORE_URI;
    }
}
