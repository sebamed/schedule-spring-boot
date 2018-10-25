/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
  +---------------------------------------------+
  | Name: UserAlreadyExistsException                                  
  | Author: Sebastian                         
  | Date: Oct 24, 2018                                                                                                                         
  +---------------------------------------------+
*/
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User with that address already exists")
public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 8960543364304737597L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}
}
