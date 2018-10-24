/**
 * SCHEDULE API
 */
package com.mudri.schedule.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mudri.schedule.base.BaseCrudInterface;
import com.mudri.schedule.dto.RoleDTO;
import com.mudri.schedule.exception.EntityAlreadyExistsException;
import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.exception.SaveFailedException;
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
		
		if(this.doesRoleExist(roleDTO.getName())) {
			throw new EntityAlreadyExistsException("Role with name: " + roleDTO.getName() + " already exists!");
		}
		
		Role role = new Role();
		role.setName(roleDTO.getName());
		role.setActive(true);

		role = this.save(role);

		if (role.getId() != null) {
			roleDTO.setId(role.getId());
			return roleDTO;
		} else {
			return new RoleDTO();
		}
	}

	public List<RoleDTO> getAllDTO() {
		Type targetRoleType = new TypeToken<List<RoleDTO>>() {
		}.getType();

		return this.modelMapper.map(this.findAll(), targetRoleType);
	}

	public RoleDTO getDTOById(Long id) {
		Role role = this.findOneById(id);
		return this.modelMapper.map(role, RoleDTO.class);
	}

	@Override
	public Role save(Role object) {
		try {
			return this.roleRepository.save(object);
		} catch (Exception e) {
			throw new SaveFailedException("Role could not be saved properly.");
		}
	}

	@Override
	public Role findOneById(Long id) {
		Optional<Role> role = this.roleRepository.findOneById(id);
		if (role.isPresent()) {
			return role.get();
		} else {
			throw new NotFoundException("No role found with ID: " + id);
		}
	}

	@Override
	public List<Role> findAll() {
		List<Role> roles = this.roleRepository.findAll();
		if (!roles.isEmpty()) {
			return roles;
		} else {
			throw new NotFoundException("No roles were added");
		}
	}

	private boolean doesRoleExist(String name) {
		Optional<Role> role = this.roleRepository.findOneByName(name);
		if (role.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public Role findOneByName(String name) {
		Optional<Role> role = this.roleRepository.findOneByName(name);
		if (role.isPresent()) {
			return role.get();
		} else {
			throw new NotFoundException("No role found with name: " + name);
		}
	}

}
