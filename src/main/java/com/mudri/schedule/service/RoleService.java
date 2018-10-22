/**
 * SCHEDULE API
 */
package com.mudri.schedule.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mudri.schedule.base.BaseCrudInterface;
import com.mudri.schedule.dto.CourseDTO;
import com.mudri.schedule.dto.RoleDTO;
import com.mudri.schedule.model.Role;
import com.mudri.schedule.repository.RoleRepository;

/*
  +---------------------------------------------+
  | Name: RoleService                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Service
public class RoleService implements BaseCrudInterface<Role> {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ModelMapper modelMapper;
	
	public RoleDTO create(RoleDTO roleDTO) {
		Role role = new Role();
		role.setName(roleDTO.getName());
		role.setActive(true);
		
		role = this.save(role);
		
		if(role.getId() != null) {
			roleDTO.setId(role.getId());
			return roleDTO;
		} else {
			return new RoleDTO();
		}
	}
	
	public List<RoleDTO> getAllDTO(){
		Type targetRoleType = new TypeToken<List<RoleDTO>>() {}.getType();
		List<RoleDTO> rolesDTO = this.modelMapper.map(this.findAll(), targetRoleType);

		return rolesDTO;
	}

	public RoleDTO getDTOById(Long id) {
		Role role = this.findOneById(id);
		if (role.getId() != null) {
			return this.modelMapper.map(role, RoleDTO.class);
		} else {
			return new RoleDTO();
		}
	}

	@Override
	public Role save(Role object) {
		return this.roleRepository.save(object);
	}

	@Override
	public Role findOneById(Long id) {
		Optional<Role> role = this.roleRepository.findOneById(id);
		if (role.isPresent()) {
			return role.get();
		} else {
			return new Role();
		}
	}

	@Override
	public List<Role> findAll() {
		List<Role> roles = this.roleRepository.findAll();
		if (roles.size() > 0) {
			return roles;
		} else {
			return Collections.emptyList();
		}
	}
	
	public Role findOneByName(String name) {
		Optional<Role> role = this.roleRepository.findOneByName(name);
		if(role.isPresent()) {
			return role.get();
		} else {
			return new Role();
		}
	}

}
