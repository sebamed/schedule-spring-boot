/**
 * SCHEDULE API
 */
package com.mudri.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mudri.schedule.dto.CourseDTO;
import com.mudri.schedule.service.CourseService;

/*
  +---------------------------------------------+
  | Name: CourseAPI                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@RestController
@RequestMapping("/api/courses")
public class CourseAPI {

	@Autowired
	CourseService courseService;

	@GetMapping("/{id}")
	public ResponseEntity<CourseDTO> handleGetDTOById(@PathVariable("id") Long id) {
		return new ResponseEntity<CourseDTO>(this.courseService.getDTOById(id), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<CourseDTO>> handleGetAllDTO() {
		return new ResponseEntity<List<CourseDTO>>(this.courseService.getAllDTO(), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<CourseDTO> handleCreateCourse(@RequestBody CourseDTO courseDTO) {
		return new ResponseEntity<CourseDTO>(this.courseService.create(courseDTO), HttpStatus.OK);
	}

}
