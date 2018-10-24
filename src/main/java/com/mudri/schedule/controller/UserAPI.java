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

import com.mudri.schedule.dto.RegisterDTO;
import com.mudri.schedule.dto.UserDTO;
import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.exception.SaveFailedException;
import com.mudri.schedule.exception.UserAlreadyExistsException;
import com.mudri.schedule.service.UserService;

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

	@PostMapping()
	public ResponseEntity<UserDTO> handleCreateUser(@RequestBody UserDTO userDTO) {
		return new ResponseEntity<UserDTO>(this.userService.create(userDTO), HttpStatus.CREATED);
	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> handleRegister(@RequestBody RegisterDTO registerDTO) {
		return new ResponseEntity<UserDTO>(this.userService.register(registerDTO), HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<List<UserDTO>> handleGetAllUsers() {
		return new ResponseEntity<List<UserDTO>>(this.userService.getAllDTO(), HttpStatus.OK);

	}

	@GetMapping("/role/{role}")
	public ResponseEntity<List<UserDTO>> handleGetAllUsersByRoleName(@PathVariable("role") String name) {
		return new ResponseEntity<List<UserDTO>>(this.userService.getAllDTOByRoleName(name), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> handleGetUserById(@PathVariable("id") Long id) {
		return new ResponseEntity<UserDTO>(this.userService.getDTOById(id), HttpStatus.OK);

	}

}
