package com.websystique.springsecurity.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websystique.springsecurity.dto.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        Result result = Result.fail("用户名密码错误，请重新输入！", e);
        //设置状态码
        //httpServletResponse.setStatus(500);
        //将 登录失败 信息打包成json格式返回
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
