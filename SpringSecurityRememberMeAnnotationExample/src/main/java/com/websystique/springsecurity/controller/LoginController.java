package com.websystique.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Controller
public class LoginController {

    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

    @GetMapping(value = "/main")
    public void main(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String path = request.getContextPath() ;
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        //获取到登陆者的权限，然后做跳转
        if (roles.contains("ROLE_ADMIN")){
            response.sendRedirect(String.format("%s%s", basePath, "admin/admin"));
            return;
        }else if (roles.contains("ROLE_USER")){
            response.sendRedirect(String.format("%s%s", basePath,"admin/admin"));
            return;
        }else if (roles.contains("ROLE_DBA")){
            response.sendRedirect(String.format("%s%s", basePath,"admin/admin"));
            return;
        }else {
            response.sendRedirect(String.format("%s%s", basePath,"403"));
        }
    }

    /*
     * 获取当前用户
     *
     * @return
     */
    public static User getCurrentUser() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = null;
        if (authentication != null) {
            principal = authentication.getPrincipal();
        }
        if (principal != null && principal instanceof User) {
            user = (User) principal;
        }
        return user;
    }

































    /*@Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private HttpServletRequest request;*/

    /**
     * security5 自定义登录方法
     *
     */
    /*@ResponseBody
    @PostMapping(value = "login")
    public String login(String ssoId, String password) {
        System.out.println("进入了 controller 中的登录方法 ............. "+ssoId+"<><><>>"+password);
        *//*if(StringUtils.isBlank(verifyCode) || !verifyCode.trim().equals("1234")){
            return ResultUtil.fail("验证码错误.");
        }*//*
        //登录 身份认证
        // 这句代码会自动执行咱们自定义的 "MyUserDetailService.java" 身份认证类
        //1: 将用户名和密码封装成UsernamePasswordAuthenticationToken  new UsernamePasswordAuthenticationToken(userAccount, userPwd)
        //2: 将UsernamePasswordAuthenticationToken传给AuthenticationManager进行身份认证   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAccount, userPwd));
        //3: 认证完毕，返回一个认证后的身份： Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAccount, userPwd));
        //4: 认证后，存储到SecurityContext里   SecurityContext securityContext = SecurityContextHolder.getContext();securityContext.setAuthentication(authentication);


        //UsernamePasswordAuthenticationToken继承AbstractAuthenticationToken实现Authentication
        //当在页面中输入用户名和密码之后首先会进入到UsernamePasswordAuthenticationToken验证(Authentication)，注意用户名和登录名都是页面传来的值
        //然后生成的Authentication会被交由AuthenticationManager来进行管理
        //而AuthenticationManager管理一系列的AuthenticationProvider，
        //而每一个Provider都会通UserDetailsService和UserDetail来返回一个
        //以UsernamePasswordAuthenticationToken实现的带用户名和密码以及权限的Authentication
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(ssoId, password));
            //将身份 存储到SecurityContext里
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext); // 这个非常重要，否则验证后将无法登陆
            return "ok";
        }catch (AuthenticationException e){
            e.printStackTrace();
            return "on";
        }
    }*/
}
