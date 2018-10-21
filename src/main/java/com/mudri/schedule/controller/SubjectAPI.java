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
@RequestMapping("/api/subject")
public class SubjectAPI {

	@Autowired
	SubjectService subjectService;

	@GetMapping("/{id}")
	public ResponseEntity<SubjectDTO> handleGetSubjectById(@PathVariable("id") Long id) {
		SubjectDTO subjectDTO = this.subjectService.getDTOById(id);
		if (subjectDTO.getId() != null) {
			return new ResponseEntity<SubjectDTO>(subjectDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<SubjectDTO>> handleGetAllSubjects() {
		List<SubjectDTO> subjectsDTO = this.subjectService.getAllDTO();
		if (subjectsDTO.size() > 0) {
			return new ResponseEntity<List<SubjectDTO>>(subjectsDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<SubjectDTO> handleCreateSubject(@RequestBody SubjectDTO subjectDTO) {
		SubjectDTO newSubjectDTO = this.subjectService.create(subjectDTO);
		if (newSubjectDTO.getId() != null) {
			return new ResponseEntity<SubjectDTO>(newSubjectDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
