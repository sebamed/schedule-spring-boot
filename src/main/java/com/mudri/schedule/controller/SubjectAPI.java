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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mudri.schedule.consts.RoleConstants;
import com.mudri.schedule.dto.SubjectDTO;
import com.mudri.schedule.service.SubjectService;
import com.mudri.schedule.utils.ReturnResponse;

/**
 * Endpoints for subjects
 * 
 * @author sebamed
 */
@RestController
@RequestMapping("/api/subjects")
public class SubjectAPI {

	@Autowired
	SubjectService subjectService;

	@GetMapping("/{id}")
    @PreAuthorize(RoleConstants.AUTH_USER_ADMIN) 
	public ResponseEntity<SubjectDTO> handleGetSubjectById(@PathVariable("id") Long id) {
		return ReturnResponse.entityGet(this.subjectService.getDTOById(id));
	}

	@GetMapping()
    @PreAuthorize(RoleConstants.AUTH_USER_ADMIN)
	public ResponseEntity<List<SubjectDTO>> handleGetAllSubjects() {
		return ReturnResponse.listGet(this.subjectService.getAllDTO());
	}

	@PostMapping()
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<SubjectDTO> handleCreateSubject(@RequestBody SubjectDTO subjectDTO) {
		return ReturnResponse.entityCreated(this.subjectService.create(subjectDTO));
	}
	
	@PutMapping()
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<SubjectDTO> handleUpdateSubject(@RequestBody SubjectDTO subjectDTO) {
		return ReturnResponse.entityCreated(this.subjectService.update(subjectDTO));
	}

}
