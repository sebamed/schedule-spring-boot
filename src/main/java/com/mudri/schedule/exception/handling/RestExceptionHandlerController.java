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
import com.mudri.schedule.exception.NoNeededSkillException;
import com.mudri.schedule.exception.NoUserInLessonException;
import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.exception.SaveFailedException;
import com.mudri.schedule.exception.UserAlreadyExistsException;
import com.mudri.schedule.exception.UserAlreadyInLessonException;

/*
  +---------------------------------------------+
  | Name: ExceptionHandler                                  
  | Author: Sebastian                         
  | Date: Oct 24, 2018                                                                                                                         
  +---------------------------------------------+
*/

@ControllerAdvice
public class RestExceptionHandlerController {

	@ExceptionHandler(NoNeededSkillException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public @ResponseBody ExceptionResponse handleNoNeededSkillException(final RuntimeException exc,
			final HttpServletRequest request) {
		return new ExceptionResponse(exc.getMessage(), request.getRequestURI(), HttpStatus.UNAUTHORIZED.toString());
	}

	@ExceptionHandler({ NotFoundException.class, NoUserInLessonException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleNotFoundException(final RuntimeException exc,
			final HttpServletRequest request) {
		return new ExceptionResponse(exc.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND.toString());
	}

	@ExceptionHandler(SaveFailedException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public @ResponseBody ExceptionResponse handleSaveFailedException(final RuntimeException exc,
			final HttpServletRequest request) {
		return new ExceptionResponse(exc.getMessage(), request.getRequestURI(), HttpStatus.NOT_ACCEPTABLE.toString());
	}

	@ExceptionHandler({ UserAlreadyExistsException.class, EntityAlreadyExistsException.class,
			UserAlreadyInLessonException.class })
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ExceptionResponse handleUserAlreadyExistsException(final RuntimeException exc,
			final HttpServletRequest request) {
		return new ExceptionResponse(exc.getMessage(), request.getRequestURI(), HttpStatus.CONFLICT.toString());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(final Exception exc, final HttpServletRequest request) {
		return new ExceptionResponse(exc.getMessage(), request.getRequestURI(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString());
	}

}
