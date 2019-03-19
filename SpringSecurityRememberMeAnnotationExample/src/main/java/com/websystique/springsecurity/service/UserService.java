package com.websystique.springsecurity.service;

import com.websystique.springsecurity.model.User;

public interface UserService {

	User findById(int id);
	
	User findBySso(String sso);
    /**
     *只能够被拥有DBA 或者ADMIN 权限的用户调用
     *@Secured({ "ROLE_DBA", "ROLE_ADMIN" })
     *
     *
     *拥有ADMIN & DBA角色的用户调用 .
     * @PreAuthorize("hasRole('ADMIN') AND hasRole('DBA')")
     *
     *  我们增加了带有@PostAuthorize注解的findById()方法。
     *  通过@PostAuthorize注解 method(User object)的返回值在Spring表达式语言中可以通过returnObject 来使用。在例子中我们确保登录用户只能获取他自己的用户对象。
     *   @PostAuthorize ("returnObject.type == authentication.name")
     *
     *
     *
     */
	
}