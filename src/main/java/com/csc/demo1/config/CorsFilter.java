package com.csc.demo1.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(filterName = "CorsFilter", urlPatterns = {"/*"})
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)  throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) res;
        // 允许来自所有域名请求
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 是否允许发送Cookie，true为允许
        response.setHeader("Access-Control-Allow-Credential", "true");
        // 设置所允许的HTTP请求方法
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, PUT, POST");
        // 服务器支持的所有头信息字段，多个字段用逗号分隔
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("headercsc","csc");

        filterChain.doFilter(req, res);
    }
}
