/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
  +---------------------------------------------+
  | Name: UserAlreadyInLessonException                                  
  | Author: Sebastian                         
  | Date: Oct 25, 2018                                                                                                                         
  +---------------------------------------------+
*/

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User was already added in the lesson!")
public class UserAlreadyInLessonException extends RuntimeException {

	private static final long serialVersionUID = -406117996546203102L;
	
	public UserAlreadyInLessonException(String message) {
		super(message);
	}

}
