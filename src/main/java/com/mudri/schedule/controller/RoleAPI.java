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

import com.mudri.schedule.dto.RoleDTO;
import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.exception.SaveFailedException;
import com.mudri.schedule.service.RoleService;

/*
  +---------------------------------------------+
  | Name: RoleAPI                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@RestController
@RequestMapping("/api/roles")
public class RoleAPI {

	@Autowired
	RoleService roleService;

	@GetMapping("/{id}")
	public ResponseEntity<RoleDTO> handleGetRoleById(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<RoleDTO>(this.roleService.getDTOById(id), HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping()
	public ResponseEntity<List<RoleDTO>> handleGetAllRoles() {
		try {
			return new ResponseEntity<List<RoleDTO>>(this.roleService.getAllDTO(), HttpStatus.OK);			
		} catch(NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		}
	}

	@PostMapping()
	public ResponseEntity<RoleDTO> handleCreateRole(@RequestBody RoleDTO roleDTO) {
		try {			
			return new ResponseEntity<RoleDTO>(this.roleService.create(roleDTO), HttpStatus.CREATED);
		} catch(SaveFailedException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
	}

}
