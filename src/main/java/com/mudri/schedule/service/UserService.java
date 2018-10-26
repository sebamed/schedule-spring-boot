/**
 * SCHEDULE API
 */
package com.mudri.schedule.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mudri.schedule.base.BaseCrudInterface;
import com.mudri.schedule.dto.RegisterDTO;
import com.mudri.schedule.dto.UserDTO;
import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.exception.SaveFailedException;
import com.mudri.schedule.exception.UserAlreadyExistsException;
import com.mudri.schedule.model.Role;
import com.mudri.schedule.model.User;
import com.mudri.schedule.repository.UserRepository;
import com.mudri.schedule.utils.Constants;
import com.mudri.schedule.utils.TargetType;

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

	public UserDTO register(RegisterDTO registerDTO) {

		if (this.doesUserExist(registerDTO.getEmail())) {
			throw new UserAlreadyExistsException("User with email: " + registerDTO.getEmail() + " already exists!");
		}

		Role role = new Role();

		role = this.roleService.findOneByName(Constants.USER_ROLE);

		User user = new User();
		user.setFieldsFromRegisterDTO(registerDTO);

		user = this.save(user);

		role.getUsers().add(user);
		this.roleService.save(role);

		return this.modelMapper.map(user, UserDTO.class);
	}

	public List<UserDTO> getAllDTOByRoleName(String name) {	
		return this.modelMapper.map(this.findAllByRoleName(name), TargetType.userType);
	}

	public UserDTO getDTOById(Long id) {
		User user = this.findOneById(id);
		if (user.getId() != null) {
			return this.modelMapper.map(user, UserDTO.class);
		} else {
			throw new NotFoundException("No user with ID: " + id);
		}
	}

	public List<UserDTO> getAllDTO() {
		return this.modelMapper.map(this.findAll(), TargetType.userType);
	}

	// TODO: SREDITI
	public UserDTO create(UserDTO userDTO) {
		User user = new User();
		Role role = this.roleService.findOneByName(Constants.USER_ROLE);
		if (role.getId() != null) {
			user = this.modelMapper.map(userDTO, User.class);
			user.setRole(role);
			user = this.save(user);
			if (user.getId() != null) {
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

	public boolean doesUserExist(String email) {
		return this.userRepository.findOneByEmail(email).isPresent();
	}

	public User findOneByEmail(String email) {
		Optional<User> user = this.userRepository.findOneByEmail(email);
		if (user.isPresent())
			return user.get();
		else
			throw new NotFoundException("No user found with email: " + email);

	}

	@Override
	public User save(User object) {
		try {
			return this.userRepository.save(object);
		} catch (Exception e) {
			throw new SaveFailedException("User not saved! email: " + object.getEmail());
		}
	}

	@Override
	public User findOneById(Long id) {
		Optional<User> user = this.userRepository.findOneById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new NotFoundException("No user found with ID: " + id);
		}
	}

	@Override
	public List<User> findAll() {
		List<User> users = this.userRepository.findAll();
		return this.returnList(users);
	}

	public List<User> findAllByRoleId(Long id) {
		return this.returnList(this.userRepository.findAllByRoleId(id));
	}

	public List<User> findAllByRoleName(String name) {
		return this.returnList(this.userRepository.findAllByRoleName(name));
	}

	private List<User> returnList(List<User> users) {
		if (!users.isEmpty()) {
			return users;
		} else {
			return Collections.emptyList();
		}
	}

}
