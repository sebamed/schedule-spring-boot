/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

/*
  +---------------------------------------------+
  | Name: SaveFailedException                                  
  | Author: Sebastian                         
  | Date: Oct 23, 2018                                                                                                                         
  +---------------------------------------------+
*/

public class SaveFailedException extends RuntimeException {

	private static final long serialVersionUID = 7804040318164834889L;
	
	public SaveFailedException(String message) {
		super(message);
	}

}
