/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author sebamed
 */

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Entity with that address already exists")
public class EmailNotSentException extends RuntimeException {

	private static final long serialVersionUID = 2066701023775864484L;
	
	public EmailNotSentException(String message) {
		super(message);
	}

}
