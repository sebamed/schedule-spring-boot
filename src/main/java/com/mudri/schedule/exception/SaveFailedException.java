/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
  +---------------------------------------------+
  | Name: SaveFailedException                                  
  | Author: Sebastian                         
  | Date: Oct 23, 2018                                                                                                                         
  +---------------------------------------------+
*/

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Saving to db failed")
public class SaveFailedException extends RuntimeException {

	private static final long serialVersionUID = 7804040318164834889L;
	
	public SaveFailedException(String message) {
		super(message);
	}

}
