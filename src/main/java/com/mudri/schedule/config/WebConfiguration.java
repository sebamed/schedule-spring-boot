/**
 * SCHEDULE API
 */
package com.mudri.schedule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mudri.schedule.interceptor.LoggerInterceptor;

/**
 * Configuration for interceptor
 * 
 * @author sebamed
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Autowired
	LoggerInterceptor loggerInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.loggerInterceptor);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
	}

}
