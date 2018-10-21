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
@RequestMapping("/api/course")
public class CourseAPI {
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("/{id}")
	public ResponseEntity<CourseDTO> handleGetDTOById(@PathVariable("id") Long id){
		CourseDTO courseDTO = this.courseService.getDTOById(id);
		if(courseDTO.getId() != null) {
			return new ResponseEntity<CourseDTO>(courseDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CourseDTO>> handleGetAllDTO(){
		List<CourseDTO> coursesDTO = this.courseService.getAllDTO();
		
		if(coursesDTO.size() > 0) {
			return new ResponseEntity<List<CourseDTO>>(coursesDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<CourseDTO> handleCreateCourse(@RequestBody CourseDTO courseDTO){
		CourseDTO newCourseDTO = this.courseService.create(courseDTO);
		if(newCourseDTO.getId() != null) {
			return new ResponseEntity<CourseDTO>(newCourseDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
