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

import com.mudri.schedule.dto.LessonDTO;
import com.mudri.schedule.dto.RegisterDTO;
import com.mudri.schedule.dto.UserDTO;
import com.mudri.schedule.dto.UserInfoDTO;
import com.mudri.schedule.service.UserService;
import com.mudri.schedule.utils.ReturnResponse;

/*
  +---------------------------------------------+
  | Name: UserAPI                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@RestController
@RequestMapping("/api/users")
public class UserAPI {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserInfoDTO> handleRegister(@RequestBody RegisterDTO registerDTO) {
		return ReturnResponse.entityGet(this.userService.register(registerDTO));
	}

	@GetMapping()
	public ResponseEntity<List<UserInfoDTO>> handleGetAllUsers() {
		return ReturnResponse.listGet(this.userService.getAllDTO());
	}

	@GetMapping("/role/{role}")
	public ResponseEntity<List<UserInfoDTO>> handleGetAllUsersByRoleName(@PathVariable("role") String name) {
		return ReturnResponse.listGet(this.userService.getAllDTOByRoleName(name));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserInfoDTO> handleGetUserById(@PathVariable("id") Long id) {
		return ReturnResponse.entityGet(this.userService.getDTOById(id));
	}
	
	@GetMapping("/{id}/lessons")
	public ResponseEntity<List<LessonDTO>> handleGetUsersLessons(@PathVariable("id") Long id){
		return ReturnResponse.listGet(this.userService.getUserLessonsDTO(id));
	}

	// depricated ne koristi se!!!
	@PostMapping()
	public ResponseEntity<UserDTO> handleCreateUser(@RequestBody UserDTO userDTO) {
		return new ResponseEntity<UserDTO>(this.userService.create(userDTO), HttpStatus.CREATED);
	}
}
