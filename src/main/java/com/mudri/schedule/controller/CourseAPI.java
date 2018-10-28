/**
 * SCHEDULE API
 */
package com.mudri.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mudri.schedule.dto.CourseDTO;
import com.mudri.schedule.service.CourseService;
import com.mudri.schedule.utils.ReturnResponse;

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
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<CourseDTO> handleGetDTOById(@PathVariable("id") Long id) {
		return ReturnResponse.entityGet(this.courseService.getDTOById(id));
	}

	@GetMapping()
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public ResponseEntity<List<CourseDTO>> handleGetAllDTO() {
		return ReturnResponse.listGet(this.courseService.getAllDTO());
	}

	@PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<CourseDTO> handleCreateCourse(@RequestBody CourseDTO courseDTO) {
		return ReturnResponse.entityCreated(this.courseService.create(courseDTO));
	}

}
