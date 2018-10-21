/**
 * 
 */
package com.mudri.schedule.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
  +---------------------------------------------+
  | Name: SubjectAPI                                  
  | Author: Sebastian                         
  | Date: Oct 21, 2018                                                                                                                         
  +---------------------------------------------+
*/

@RestController
@RequestMapping("/api/subject")
public class SubjectAPI {
	
	@GetMapping("/{id}")
	public ResponseEntity<Void> handleGetSubjectById(@PathVariable("id") Long id){
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
