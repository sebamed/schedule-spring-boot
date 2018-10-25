/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
  +---------------------------------------------+
  | Name: NoUserInLessonException                                  
  | Author: Sebastian                         
  | Date: Oct 25, 2018                                                                                                                         
  +---------------------------------------------+
*/

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User was never added to this lesson!")
public class NoUserInLessonException extends RuntimeException {

	private static final long serialVersionUID = -937484789060618744L;

	public NoUserInLessonException(String message) {
		super(message);
	}

}
