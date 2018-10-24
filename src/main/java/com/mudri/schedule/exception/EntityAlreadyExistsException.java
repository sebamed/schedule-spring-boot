/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
  +---------------------------------------------+
  | Name: EntityAlreadyExistsException                                  
  | Author: Sebastian                         
  | Date: Oct 24, 2018                                                                                                                         
  +---------------------------------------------+
*/

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Entity with that address already exists")
public class EntityAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 4283637199769201069L;
	
	public EntityAlreadyExistsException(String message) {
		super(message);
	}

}
