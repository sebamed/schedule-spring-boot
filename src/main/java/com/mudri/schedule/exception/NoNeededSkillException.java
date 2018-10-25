/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
  +---------------------------------------------+
  | Name: NoNeededSkillException                                  
  | Author: Sebastian                         
  | Date: Oct 25, 2018                                                                                                                         
  +---------------------------------------------+
*/

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User has no needed skill for confirmig the lesson!")
public class NoNeededSkillException extends RuntimeException {

	 private static final long serialVersionUID = -7483630507069815910L;
	 
	 public NoNeededSkillException(String message) {
		 super(message);
	 }

}
