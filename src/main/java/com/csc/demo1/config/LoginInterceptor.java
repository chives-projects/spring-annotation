package com.csc.demo1.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: login拦截器
 * @PackageName: com.csc.demo1.config
 * @Author: CSC
 * @Create: 2020-07-09 16:29
 * @Version: 1.0
 */
//@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * @Description: 预处理回调方法，实现处理器的预处理
     * @param: request:
     * @param: response:
     * @param: handler:
     * @return: boolean
     * @Author: CSC
     * @Date: 2020/7/9 16:30
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("开始拦截.........");
        return true;
    }

    /**
     * @Description:
     * @Author: CSC 
     * @Date: 2020/7/9 16:32
     **/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle....");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion-----");
    }
}
