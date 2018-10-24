/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception.handling;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mudri.schedule.exception.EntityAlreadyExistsException;
import com.mudri.schedule.exception.ExceptionResponse;
import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.exception.SaveFailedException;
import com.mudri.schedule.exception.UserAlreadyExistsException;

/*
  +---------------------------------------------+
  | Name: ExceptionHandler                                  
  | Author: Sebastian                         
  | Date: Oct 24, 2018                                                                                                                         
  +---------------------------------------------+
*/

@ControllerAdvice
public class RestExceptionHandlerController {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleNotFoundException(final RuntimeException exc, final HttpServletRequest request) {
		return new ExceptionResponse(exc.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND.toString());
	}
	
	@ExceptionHandler(SaveFailedException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public @ResponseBody ExceptionResponse handleSaveFailedException(final RuntimeException exc, final HttpServletRequest request) {
		return new ExceptionResponse(exc.getMessage(), request.getRequestURI(), HttpStatus.NOT_ACCEPTABLE.toString());
	}
	
	@ExceptionHandler({UserAlreadyExistsException.class, EntityAlreadyExistsException.class})
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ExceptionResponse handleUserAlreadyExistsException(final RuntimeException exc, final HttpServletRequest request) {
		return new ExceptionResponse(exc.getMessage(), request.getRequestURI(), HttpStatus.CONFLICT.toString());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(final Exception exc, final HttpServletRequest request) {
		return new ExceptionResponse(exc.getMessage(), request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
	}

}
