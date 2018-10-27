/**
 * SCHEDULE API
 */
package com.mudri.schedule.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/*
  +---------------------------------------------+
  | Name: ReturnResponse                                  
  | Author: Sebastian                         
  | Date: Oct 27, 2018                                                                                                                         
  +---------------------------------------------+
*/

public class ReturnResponse {
	
	public static <T> ResponseEntity<List<T>> listGet(List<T> list) {
		return list.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<List<T>>(list, HttpStatus.OK);
	}
	
	public static <T> ResponseEntity<T> entityGet(T obj) {
		return new ResponseEntity<T>(obj, HttpStatus.OK);
	}
	
	public static <T> ResponseEntity<T> entityCreated(T obj){
		return new ResponseEntity<T>(obj, HttpStatus.CREATED);
	}

}
