/**
 * SCHEDULE API
 */
package com.mudri.schedule.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
  +---------------------------------------------+
  | Name: LoggerInterceptor                                  
  | Author: Sebastian                         
  | Date: Oct 26, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Component
public class LoggerInterceptor implements HandlerInterceptor {

	Logger logger = LoggerFactory.getLogger("requests");

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception arg3) {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {
		logger.info("SUCCESSFUL: " + request.getRequestURI() + " | " + request.getMethod() + " | " + response.getStatus());
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		logger.info("REQUEST: " + request.getRequestURI() + " | " + request.getMethod());
		return true;
	}
}
