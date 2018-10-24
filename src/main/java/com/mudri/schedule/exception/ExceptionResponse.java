/**
 * SCHEDULE API
 */
package com.mudri.schedule.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  +---------------------------------------------+
  | Name: ExceptionResponse                                  
  | Author: Sebastian                         
  | Date: Oct 24, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

	private String errorMessage;
	private String requestedURI;
	private String errorCode;
	
}