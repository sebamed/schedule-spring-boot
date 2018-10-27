/**
 * SCHEDULE API
 */
package com.mudri.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mudri.schedule.dto.RoleDTO;
import com.mudri.schedule.service.RoleService;
import com.mudri.schedule.utils.ReturnResponse;

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
		return ReturnResponse.entityGet(this.roleService.getDTOById(id));
	}

	@GetMapping()
	public ResponseEntity<List<RoleDTO>> handleGetAllRoles() {
		return ReturnResponse.listGet(this.roleService.getAllDTO());
	}

	@PostMapping()
	public ResponseEntity<RoleDTO> handleCreateRole(@RequestBody RoleDTO roleDTO) {
		return ReturnResponse.entityCreated(this.roleService.create(roleDTO));
	}

}
