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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mudri.schedule.base.BaseCrudInterface;
import com.mudri.schedule.consts.Constants;
import com.mudri.schedule.dto.LessonDTO;
import com.mudri.schedule.dto.RegisterDTO;
import com.mudri.schedule.dto.SubjectDTO;
import com.mudri.schedule.dto.UserInfoDTO;
import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.exception.SaveFailedException;
import com.mudri.schedule.exception.UserAlreadyExistsException;
import com.mudri.schedule.model.Role;
import com.mudri.schedule.model.Subject;
import com.mudri.schedule.model.User;
import com.mudri.schedule.repository.UserRepository;
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
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SubjectService subjectService;

	@Autowired
	RoleService roleService;
	
	public List<UserInfoDTO> getAllUsersBySubject(Long id){
		return this.modelMapper.map(this.userRepository.findAllBySkillsId(id), TargetType.userInfoType);
	}
	
	public List<SubjectDTO> updateUserSkills(Long id, List<SubjectDTO> skillsDTO){
		List<Subject> subjects = new ArrayList<>();
		for(SubjectDTO skill : skillsDTO) {
			subjects.add(this.subjectService.findOneById(skill.getId()));
		}
		
		User user = this.findOneById(id);
		for(Subject subject : this.subjectService.findAll()) {
			if(subject.getSkilledUsers().contains(user)) {
				subject.getSkilledUsers().remove(user);
			}
			
		}
		
		for(Subject subject : subjects) {
			subject.getSkilledUsers().add(user);
			this.subjectService.save(subject);
		}
		
		user.setSkills(subjects);
		return this.modelMapper.map(this.save(user).getSkills(), TargetType.subjectType);
	}
	
	public List<SubjectDTO> getUserSkillsDTO(Long id){
		return this.modelMapper.map(this.findOneById(id).getSkills(), TargetType.subjectType);
	}

	public List<LessonDTO> getUserLessonsDTO(Long id) {
		return this.modelMapper.map(this.findOneById(id).getLessons(), TargetType.lessonType);
	}

	public UserInfoDTO register(RegisterDTO registerDTO) {
		if (this.doesUserExist(registerDTO.getEmail())) {
			throw new UserAlreadyExistsException("User with email: " + registerDTO.getEmail() + " already exists!");
		}

		Role role = this.roleService.findOneByName(Constants.USER_ROLE);

		User user = new User();
		user.setFieldsFromRegisterDTO(registerDTO);
		user.setPassword(this.bCryptPasswordEncoder.encode(registerDTO.getPassword()));
		user.setRole(role);

		role.getUsers().add(user);
		this.roleService.save(role);

		return this.modelMapper.map(this.save(user), UserInfoDTO.class);
	}

	public List<UserInfoDTO> getAllDTOByRoleName(String name) {
		return this.modelMapper.map(this.findAllByRoleName(name), TargetType.userInfoType);
	}

	public UserInfoDTO getDTOById(Long id) {
		return this.modelMapper.map(this.findOneById(id), UserInfoDTO.class);
	}

	public UserInfoDTO getDTOByEmail(String email) {
		return this.modelMapper.map(this.findOneByEmail(email), UserInfoDTO.class);
	}

	public List<UserInfoDTO> getAllDTO() {
		return this.modelMapper.map(this.findAll(), TargetType.userInfoType);
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
		return !users.isEmpty() ? users : Collections.emptyList();
	}

}
