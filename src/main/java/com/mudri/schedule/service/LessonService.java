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
import com.mudri.schedule.dto.CreateLessonDTO;
import com.mudri.schedule.dto.LessonDTO;
import com.mudri.schedule.dto.UserDTO;
import com.mudri.schedule.dto.UserLessonDTO;
import com.mudri.schedule.exception.NoNeededSkillException;
import com.mudri.schedule.exception.NoUserInLessonException;
import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.exception.SaveFailedException;
import com.mudri.schedule.exception.UserAlreadyInLessonException;
import com.mudri.schedule.model.Course;
import com.mudri.schedule.model.Lesson;
import com.mudri.schedule.model.AppUser;
import com.mudri.schedule.repository.LessonRepository;
import com.mudri.schedule.utils.TargetType;

/*
  +---------------------------------------------+
  | Name: LessonService                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Service
public class LessonService implements BaseCrudInterface<Lesson> {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	LessonRepository lessonRepository;

	@Autowired
	UserService userService;

	@Autowired
	CourseService courseService;
		
	public List<UserDTO> getStudentsDTO(Long id){
		Lesson lesson = this.findOneById(id);
		
		return this.modelMapper.map(lesson.getStudents(), TargetType.userType);		
	}
	
	public LessonDTO leave(UserLessonDTO userLessonDTO) {
		Lesson lesson = this.findOneById(userLessonDTO.getLessonId());
		AppUser user = this.userService.findOneById(userLessonDTO.getUserId());
		
		// if user is not in the lesson at all
		if(!(lesson.getStudents().contains(user))) {
			throw new NoUserInLessonException("No user with ID: " + userLessonDTO.getUserId() + " in this lesson");
		}
		
		user.getLessons().remove(lesson);
		this.userService.save(user);
		
		lesson.getStudents().remove(user);
		return this.modelMapper.map(this.save(lesson), LessonDTO.class);
	}

	public LessonDTO join(UserLessonDTO userLessonDTO) {
		Lesson lesson = this.findOneById(userLessonDTO.getLessonId());
		AppUser user = this.userService.findOneById(userLessonDTO.getUserId());
		
		// if user is already in lesson
		if(lesson.getStudents().contains(user)) {
			throw new UserAlreadyInLessonException("User with ID: " + userLessonDTO.getUserId() + " already joined this lesson");
		}
		
		user.getLessons().add(lesson);
		this.userService.save(user);
		
		lesson.getStudents().add(user);
		return this.modelMapper.map(this.save(lesson), LessonDTO.class);
	}

	public LessonDTO confirm(UserLessonDTO userLessonDTO) {
		Lesson lesson = this.findOneById(userLessonDTO.getLessonId());
		AppUser user = this.userService.findOneById(userLessonDTO.getUserId());

		// checks if user has the needed skill for this lesson
		if (!(user.getSkills().contains(lesson.getCourse().getSubject()))) {
			throw new NoNeededSkillException("User with ID:" + userLessonDTO.getUserId()
					+ " cannot confirm lesson with ID: " + userLessonDTO.getLessonId());
		}
		
		// TODO: napraviti email servis sa thymeleaf html templateom koji ce slati
		// mejlove svim studentima iz lessona
		lesson.confirm();
		lesson.setTeacher(user);

		user.getTeachedLessons().add(lesson);
		this.userService.save(user);

		return this.modelMapper.map(this.save(lesson), LessonDTO.class);
	}

	public LessonDTO create(CreateLessonDTO createLessonDTO) {
		AppUser user = this.userService.findOneById(createLessonDTO.getUserID());
		Course course = this.courseService.save(this.modelMapper.map(createLessonDTO.getCourse(), Course.class));
		Lesson lesson = new Lesson();

		lesson.getStudents().add(user);
		lesson.setCourse(course);
		lesson = this.save(lesson);

		user.getLessons().add(lesson);
		this.userService.save(user);
		return this.modelMapper.map(lesson, LessonDTO.class);
	}

	public List<LessonDTO> getAllDTO() {
		return this.modelMapper.map(this.findAll(), TargetType.lessonType);
	}

	public LessonDTO getDTOById(Long id) {
		return this.modelMapper.map(this.findOneById(id), LessonDTO.class);
	}

	@Override
	public Lesson save(Lesson object) {
		try {
			return this.lessonRepository.save(object);
		} catch (Exception e) {
			throw new SaveFailedException("Lesson not saved!");
		}
	}

	@Override
	public Lesson findOneById(Long id) {
		Optional<Lesson> lesson = this.lessonRepository.findOneById(id);
		if (lesson.isPresent()) {
			return lesson.get();
		} else {
			throw new NotFoundException("No lesson with ID: " + id);
		}
	}

	@Override
	public List<Lesson> findAll() {
		List<Lesson> lessons = this.lessonRepository.findAll();
		if (!lessons.isEmpty()) {
			return lessons;
		} else {
			return Collections.emptyList();
		}
	}

}
