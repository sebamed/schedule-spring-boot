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

import com.mudri.schedule.consts.RoleConstants;
import com.mudri.schedule.dto.LessonDTO;
import com.mudri.schedule.dto.SubjectDTO;
import com.mudri.schedule.dto.UserInfoDTO;
import com.mudri.schedule.service.UserService;
import com.mudri.schedule.utils.ReturnResponse;

/**
 * Endpoints for users
 * 
 * @author sebamed
 */
@RestController
@RequestMapping("/api/users")
public class UserAPI {

	@Autowired
	UserService userService;

	@GetMapping()
    @PreAuthorize(RoleConstants.AUTH_USER_ADMIN)
	public ResponseEntity<List<UserInfoDTO>> handleGetAllUsers() {
		return ReturnResponse.listGet(this.userService.getAllDTO());
	}

	@GetMapping("/role/{role}")
    @PreAuthorize(RoleConstants.AUTH_USER_ADMIN)
	public ResponseEntity<List<UserInfoDTO>> handleGetAllUsersByRoleName(@PathVariable("role") String name) {
		return ReturnResponse.listGet(this.userService.getAllDTOByRoleName(name));
	}

	@GetMapping("/{id}")
    @PreAuthorize(RoleConstants.AUTH_USER_ADMIN)
	public ResponseEntity<UserInfoDTO> handleGetUserById(@PathVariable("id") Long id) {
		return ReturnResponse.entityGet(this.userService.getDTOById(id));
	}
	
	@GetMapping("/{id}/lessons")
    @PreAuthorize(RoleConstants.AUTH_USER_ADMIN)
	public ResponseEntity<List<LessonDTO>> handleGetUsersLessons(@PathVariable("id") Long id){
		return ReturnResponse.listGet(this.userService.getUserLessonsDTO(id));
	}
	
	@GetMapping("/{id}/skills")
    @PreAuthorize(RoleConstants.AUTH_USER_ADMIN)
	public ResponseEntity<List<SubjectDTO>> handleGetUsersSkills(@PathVariable("id") Long id){
		return ReturnResponse.listGet(this.userService.getUserSkillsDTO(id));
	}
	
	@PostMapping("/{id}/skills")
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<List<SubjectDTO>> handleUpdateUsersSkills(@PathVariable("id") Long id, @RequestBody List<SubjectDTO> skillsDTO){
		return ReturnResponse.listGet(this.userService.updateUserSkills(id, skillsDTO));
	}

	

}
