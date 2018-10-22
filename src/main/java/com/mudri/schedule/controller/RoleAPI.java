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
import com.mudri.schedule.service.RoleService;

/*
  +---------------------------------------------+
  | Name: RoleAPI                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@RestController
@RequestMapping("/api/role")
public class RoleAPI {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("/{id}")
	public ResponseEntity<RoleDTO> handleGetRoleById(@PathVariable("id") Long id){
		RoleDTO roleDTO = this.roleService.getDTOById(id);
		if(roleDTO.getId() != null) {
			return new ResponseEntity<RoleDTO>(roleDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<RoleDTO>> handleGetAllRoles(){
		List<RoleDTO> rolesDTO = this.roleService.getAllDTO();
		if(rolesDTO.size() > 0) {
			return new ResponseEntity<List<RoleDTO>>(rolesDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@PostMapping("/")
	public ResponseEntity<RoleDTO> handleCreateRole(@RequestBody RoleDTO roleDTO){
		RoleDTO newSoleDTO = this.roleService.create(roleDTO);
		if(newSoleDTO.getId() != null) {
			return new ResponseEntity<RoleDTO>(newSoleDTO, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
