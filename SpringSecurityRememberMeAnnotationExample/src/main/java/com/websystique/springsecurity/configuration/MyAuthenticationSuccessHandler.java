package com.websystique.springsecurity.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.websystique.springsecurity.dto.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();
        Result result = Result.success("登录成功！", authentication);
        //将 authention 信息打包成json格式返回
        //response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }




   /* public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String path = request.getContextPath() ;
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        //获取到登陆者的权限，然后做跳转
        if (roles.contains("ROLE_ADMIN")){
            response.sendRedirect(String.format("%s%s", basePath, "index"));
            return;
        }else if (roles.contains("ROLE_USER")){
            response.sendRedirect("data");
            return;
        }else if (roles.contains("ROLE_DBA")){
            response.sendRedirect("data");
            return;
        }else {
            response.sendRedirect("403");
        }
        *//*if (roles.contains("ROLE_ADMIN")){
            response.sendRedirect(basePath+"index");
            return;
        }
        response.sendRedirect(basePath+"welcome");*//*
    }*/
}
