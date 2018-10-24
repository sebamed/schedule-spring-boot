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

import com.mudri.schedule.dto.SubjectDTO;
import com.mudri.schedule.service.SubjectService;

/*
  +---------------------------------------------+
  | Name: SubjectAPI                                  
  | Author: Sebastian                         
  | Date: Oct 21, 2018                                                                                                                         
  +---------------------------------------------+
*/

@RestController
@RequestMapping("/api/subjects")
public class SubjectAPI {

	@Autowired
	SubjectService subjectService;

	@GetMapping("/{id}")
	public ResponseEntity<SubjectDTO> handleGetSubjectById(@PathVariable("id") Long id) {
		return new ResponseEntity<SubjectDTO>(this.subjectService.getDTOById(id), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<SubjectDTO>> handleGetAllSubjects() {
		return new ResponseEntity<List<SubjectDTO>>(this.subjectService.getAllDTO(), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<SubjectDTO> handleCreateSubject(@RequestBody SubjectDTO subjectDTO) {
		return new ResponseEntity<SubjectDTO>(this.subjectService.create(subjectDTO), HttpStatus.CREATED);
	}

}
