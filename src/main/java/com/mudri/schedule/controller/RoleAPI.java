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
import com.mudri.schedule.dto.RoleDTO;
import com.mudri.schedule.service.RoleService;
import com.mudri.schedule.utils.ReturnResponse;

/**
 * Endpoints for roles
 * 
 * @author sebamed
 */
@RestController
@RequestMapping("/api/roles")
public class RoleAPI {

	@Autowired
	RoleService roleService;

	@GetMapping("/{id}")
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<RoleDTO> handleGetRoleById(@PathVariable("id") Long id) {
		return ReturnResponse.entityGet(this.roleService.getDTOById(id));
	}

	@GetMapping()
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<List<RoleDTO>> handleGetAllRoles() {
		return ReturnResponse.listGet(this.roleService.getAllDTO());
	}

	@PostMapping()
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<RoleDTO> handleCreateRole(@RequestBody RoleDTO roleDTO) {
		return ReturnResponse.entityCreated(this.roleService.create(roleDTO));
	}

}
