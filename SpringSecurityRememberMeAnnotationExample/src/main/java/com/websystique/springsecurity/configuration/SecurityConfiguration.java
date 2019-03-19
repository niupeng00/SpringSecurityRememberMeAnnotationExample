package com.websystique.springsecurity.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	
	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new CustomPasswordEncoder());
	}


	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/login").permitAll() //不需要验证，放行
			.antMatchers("/admin/**").access("hasRole('ADMIN')") //admin权限可以访问
			.antMatchers("/db/**", "/data/").access("hasRole('DBA')") //dba权限才可以访问
			.antMatchers("/user/**").access("hasRole('USER')")
			.and().headers().frameOptions().disable()
			.and().formLogin().loginPage("/login")// 自定义登录界面,
			//.loginProcessingUrl("/login")
			.loginProcessingUrl("/login")
			.failureUrl("/error")//用户密码错误跳转接口
			.usernameParameter("ssoId").passwordParameter("password") // 启用用户名密码
			.successHandler(new MyAuthenticationSuccessHandler())// 自定义登录界面,登录成功后根据权限跳转不同的页面

            .failureHandler(new MyAuthenticationFailureHandler()) //登录失败后 MyAuthenticationFailureHandler 类中onAuthenticationFailure（）被调用
			.and().rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400) // 启用 remember me
			.and().csrf()
			.and().exceptionHandling().accessDeniedPage("/Access_Denied"); // 处理异常，拒绝访问就重定向到 403 页面
		//session管理
		//session失效后跳转
		http.sessionManagement()
				.invalidSessionUrl("/login");

		http.sessionManagement()//只允许一个用户登录,如果同一个账户两次登录,那么第一个账户将被踢下线,跳转到登录页面
				.maximumSessions(1)
				.sessionRegistry(sessionRegistry())
				.expiredUrl("/login.html");



            /*.and().exceptionHandling()
                // 认证配置当用户请求了一个受保护的资源，但是用户没有通过登录认证，则抛出登录认证异常，MyAuthenticationEntryPointHandler类中commence()就会调用
                .authenticationEntryPoint(myAuthenticationEntryPoint())
                //用户已经通过了登录认证，在访问一个受保护的资源，但是权限不够，则抛出授权异常，MyAccessDeniedHandler类中handle()就会调用
                .accessDeniedHandler(myAccessDeniedHandler())*/

	}

	/**
	 *  解决 无法直接注入 AuthenticationManager
	 * @return
	 * @throws Exception
	 */
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		return tokenRepositoryImpl;
	}


	//session失效跳转
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
		return new SimpleRedirectSessionInformationExpiredStrategy("/login.html");
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	//SpringSecurity内置的session监听器
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	/*@Bean
	public MyValidCodeProcessingFilter myValidCodeProcessingFilter() throws Exception {
		MyValidCodeProcessingFilter filter = new MyValidCodeProcessingFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		//设置登陆成功后跳转的URL
		filter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/index.html"));
		//设置登陆失败后跳转的URL
		//如果不设置这两个属性，会导致登陆成功后访问默认地址/
		filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login.html?error=1"));
		filter.setSessionAuthenticationStrategy(new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry));
		return filter;
	}*/
}
