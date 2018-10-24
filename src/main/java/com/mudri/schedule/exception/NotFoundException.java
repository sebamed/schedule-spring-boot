/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
  +---------------------------------------------+
  | Name: NotFoundException                                  
  | Author: Sebastian                         
  | Date: Oct 23, 2018                                                                                                                         
  +---------------------------------------------+
*/

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2743356596093145401L;

	public NotFoundException(String message) {
		super(message);
	}

}
