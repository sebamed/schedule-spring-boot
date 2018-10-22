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
		UserDTO newUserDTO = this.userService.create(userDTO);
		if(newUserDTO.getId() != null) {
			return new ResponseEntity<UserDTO>(newUserDTO, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> handleRegister(@RequestBody RegisterDTO registerDTO){
		UserDTO userDTO = this.userService.register(registerDTO);
		if(userDTO.getId() != null) {
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> handleGetAllUsers(){
		List<UserDTO> usersDTO = this.userService.getAllDTO();
		if(!usersDTO.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/role/{role}")
	public ResponseEntity<List<UserDTO>> handleGetAllUsersByRoleName(@PathVariable("role") String name){
		List<UserDTO> usersDTO = this.userService.getAllDTOByRoleName(name);
		if(!usersDTO.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> handleGetUserById(@PathVariable("id") Long id){
		UserDTO userDTO = this.userService.getDTOById(id);
		if(userDTO.getId() != null) {
			return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
