/**
 * SCHEDULE API
 */
package com.mudri.schedule.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mudri.schedule.base.BaseCrudInterface;
import com.mudri.schedule.dto.UserDTO;
import com.mudri.schedule.model.Role;
import com.mudri.schedule.model.User;
import com.mudri.schedule.repository.UserRepository;

/*
  +---------------------------------------------+
  | Name: UserService                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Service
public class UserService implements BaseCrudInterface<User> {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleService roleService;
	
	public List<UserDTO> getAllDTOByRoleName(String name){
		List<UserDTO> usersDTO = new ArrayList<>();
		
		for(User user : this.findAllByRoleName(name)) {
			usersDTO.add(this.modelMapper.map(user, UserDTO.class));
		}
		
		return usersDTO;
	}
	
	public UserDTO getDTOById(Long id) {
		User user = this.findOneById(id);
		if(user.getId() != null) {
			return this.modelMapper.map(user, UserDTO.class);
		} else {
			return new UserDTO();
		}
	}
	
	public List<UserDTO> getAllDTO(){
		List<UserDTO> usersDTO = new ArrayList<>();
		
		for(User user : this.findAll()) {
			usersDTO.add(this.modelMapper.map(user, UserDTO.class));
		}
		
		return usersDTO;
	}
	
	public UserDTO create(UserDTO userDTO) {
		User user = new User();
		Role role = this.roleService.findOneByName("USER");
		if(role.getId() != null) {
			user = this.modelMapper.map(userDTO, User.class);
			user.setLessons(null);
			user.setTeachedLessons(null);
			user.setSkills(null);
			user.setRole(role);
			user = this.save(user);
			if(user.getId() != null) {
				role.getUsers().add(user);
				this.roleService.save(role);
				return this.modelMapper.map(user, UserDTO.class);
			} else {
				return new UserDTO();
			}	
		} else {
			return new UserDTO();
		}
	}

	@Override
	public User save(User object) {
		return this.userRepository.save(object);
	}

	@Override
	public User findOneById(Long id) {
		Optional<User> user = this.userRepository.findOneById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return new User();
		}
	}

	@Override
	public List<User> findAll() {
		List<User> users = this.userRepository.findAll();
		return this.returnList(users);
	}
	
	public List<User> findAllByRoleId(Long id){
		List<User> users = this.userRepository.findAllByRoleId(id);
		return this.returnList(users);
	}
	
	public List<User> findAllByRoleName(String name){
		List<User> users = this.userRepository.findAllByRoleName(name);
		return this.returnList(users);
	}
	
	private List<User> returnList(List<User> users){
		if(users.size() > 0) {
			return users;
		} else {
			return Collections.emptyList();
		}
	}

}
