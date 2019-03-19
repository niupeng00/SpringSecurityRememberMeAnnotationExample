package com.websystique.springsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.websystique.springsecurity")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	public final static String CHARACTER_ENCODING = "UTF-8";

	/**
	 * thymeleaf模板引擎参数
	 */
	public final static String TEMPLATE_PREFIX = "/WEB-INF/templates/";
	public final static String TEMPLATE_SUFFIX = ".html";
	public final static Boolean TEMPLATE_CACHEABLE = false;
	public final static String TEMPLATE_MODE = "HTML5";
	public final static Integer TEMPLATE_ORDER = 1;

	/**
	 * 模板解析器
	 *
	 * @return
	 */
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setPrefix(TEMPLATE_PREFIX);
		templateResolver.setSuffix(TEMPLATE_SUFFIX);
		templateResolver.setCacheable(TEMPLATE_CACHEABLE);
		templateResolver.setCharacterEncoding(CHARACTER_ENCODING);
		templateResolver.setTemplateMode(TEMPLATE_MODE);
		templateResolver.setOrder(TEMPLATE_ORDER);
		return templateResolver;
	}

	/**
	 * 模板引擎
	 *
	 * @return
	 */
	@Bean
	public SpringTemplateEngine springTemplateEngine(SpringResourceTemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}

	/**
	 * 视图解析器
	 *
	 * @return
	 */
	@Bean
	public ThymeleafViewResolver viewResolver(SpringTemplateEngine springTemplateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(springTemplateEngine);
		viewResolver.setCharacterEncoding(CHARACTER_ENCODING);
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 通过 "/home.html" 请求, 来访问 /resource/static/home.html 静态资源
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	/*@Bean(name="HelloWorld")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	*//*
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     *
     *//*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }*/
}