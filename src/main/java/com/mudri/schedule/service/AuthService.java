/**
 * SCHEDULE API
 */
package com.mudri.schedule.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mudri.schedule.config.security.DomainUserDetailsService;
import com.mudri.schedule.consts.Constants;
import com.mudri.schedule.dto.LoginDTO;
import com.mudri.schedule.dto.RegisterDTO;
import com.mudri.schedule.dto.UserInfoDTO;
import com.mudri.schedule.exception.UserAlreadyExistsException;
import com.mudri.schedule.model.User;
import com.mudri.schedule.model.Role;

/*
  +---------------------------------------------+
  | Name: AuthService                                  
  | Author: Sebastian                         
  | Date: Oct 28, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Service
public class AuthService {

	@Autowired
	DomainUserDetailsService domainUserDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	public User login(LoginDTO loginDTO) {
		domainUserDetailsService.loadUserByUsername(loginDTO.getEmail());
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginDTO.getEmail(), loginDTO.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return this.userService.findOneByEmail(loginDTO.getEmail());
	}

	public UserInfoDTO register(RegisterDTO registerDTO) {
		if (this.userService.doesUserExist(registerDTO.getEmail())) {
			throw new UserAlreadyExistsException("User with email: " + registerDTO.getEmail() + " already exists!");
		}

		Role role = new Role();
		role = this.roleService.findOneByName(Constants.USER_ROLE);

		User user = new User();
		user.setFieldsFromRegisterDTO(registerDTO);
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		user.setRole(role);

		role.getUsers().add(user);
		this.roleService.save(role);

		return this.modelMapper.map(this.userService.save(user), UserInfoDTO.class);
	}

}
