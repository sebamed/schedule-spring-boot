/**
 * SCHEDULE API
 */
package com.mudri.schedule.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mudri.schedule.interceptor.LoggerInterceptor;

/*
  +---------------------------------------------+
  | Name: InterceptorConfirugration                                  
  | Author: Sebastian                         
  | Date: Oct 26, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Configuration
public class InterceptorConfirugration implements WebMvcConfigurer {

	@Autowired
	LoggerInterceptor loggerInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.loggerInterceptor);
	}

}
