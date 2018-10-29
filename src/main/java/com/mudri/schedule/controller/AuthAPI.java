/**
 * SCHEDULE API
 */
package com.mudri.schedule.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mudri.schedule.dto.LoginDTO;
import com.mudri.schedule.dto.RegisterDTO;
import com.mudri.schedule.dto.SignInResponseDTO;
import com.mudri.schedule.dto.UserInfoDTO;
import com.mudri.schedule.model.AppUser;
import com.mudri.schedule.service.AuthService;
import com.mudri.schedule.utils.ReturnResponse;
import com.mudri.schedule.utils.TokenProvider;

/*
  +---------------------------------------------+
  | Name: AuthAPI                                  
  | Author: Sebastian                         
  | Date: Oct 28, 2018                                                                                                                         
  +---------------------------------------------+
*/

@RestController
@RequestMapping("/api/auth")
public class AuthAPI {
	
	@Autowired
	AuthService authService;
	
	@Autowired
	TokenProvider tokenProvider;
	
	@Autowired
	ModelMapper modelMapper;

	
	@PostMapping("/register")
	public ResponseEntity<UserInfoDTO> handleRegister(@RequestBody RegisterDTO registerDTO) {
		return ReturnResponse.entityCreated(this.authService.register(registerDTO));
	}
	
	@PostMapping("/login")
	public ResponseEntity<SignInResponseDTO> handleLogin(@RequestBody LoginDTO loginDTO){
		AppUser user = this.authService.login(loginDTO);
		String token = tokenProvider.generateToken(user);
		return new ResponseEntity<SignInResponseDTO>(new SignInResponseDTO(this.modelMapper.map(user, UserInfoDTO.class), token), HttpStatus.OK);
	}

}
