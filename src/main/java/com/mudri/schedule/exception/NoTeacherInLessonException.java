/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
  +---------------------------------------------+
  | Name: NoTeacherInLesson                                  
  | @author sebamed                         
  | Date: Oct 30, 2018                                                                                                                         
  +---------------------------------------------+
*/

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No teacher in lesson yet!")
public class NoTeacherInLessonException extends RuntimeException {

	private static final long serialVersionUID = 6107193290785393966L;
	
	public NoTeacherInLessonException(String message) {
		super(message);
	}

}
