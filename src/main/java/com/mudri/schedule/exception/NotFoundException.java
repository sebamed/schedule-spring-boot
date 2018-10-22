/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

/*
  +---------------------------------------------+
  | Name: NotFoundException                                  
  | Author: Sebastian                         
  | Date: Oct 23, 2018                                                                                                                         
  +---------------------------------------------+
*/

public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 2743356596093145401L;

	public NotFoundException(String message) {
		super(message);
	}
	
}
