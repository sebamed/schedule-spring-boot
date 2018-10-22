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
import com.mudri.schedule.dto.CreateLessonDTO;
import com.mudri.schedule.dto.LessonDTO;
import com.mudri.schedule.model.Course;
import com.mudri.schedule.model.Lesson;
import com.mudri.schedule.model.User;
import com.mudri.schedule.repository.LessonRepository;

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
	
	public LessonDTO create(CreateLessonDTO lessonDTO) {
		
		User user = this.userService.findOneById(lessonDTO.getUserID());
		Course course = this.courseService.save(this.modelMapper.map(lessonDTO.getCourse(), Course.class));
		if(user.getId() != null && course.getId() != null) {
			Lesson lesson = new Lesson();
			lesson.getStudents().add(user);
			lesson.setCourse(course);
			lesson = this.save(lesson);
			
			if(lesson.getId() == null) {
				return new LessonDTO();
			} else {				
				user.getLessons().add(lesson);
				this.userService.save(user);
				return this.modelMapper.map(lesson, LessonDTO.class);
			}
		} else {
			return new LessonDTO();
		}
	}
	
	public List<LessonDTO> getAllDTO(){
		Type targetLessonType = new TypeToken<List<LessonDTO>>() {}.getType();
		List<LessonDTO> lessonsDTO = this.modelMapper.map(this.findAll(), targetLessonType);

		return lessonsDTO;
	}
	
	public LessonDTO getDTOById(Long id) {
		Lesson lesson = this.findOneById(id);
		if(lesson.getId() != null) {
			return this.modelMapper.map(lesson, LessonDTO.class);
		} else {
			return new LessonDTO();
		}
	}

	@Override
	public Lesson save(Lesson object) {
		return this.lessonRepository.save(object);
	}

	@Override
	public Lesson findOneById(Long id) {
		Optional<Lesson> lesson = this.lessonRepository.findOneById(id);
		if(lesson.isPresent()) {
			return lesson.get();
		} else {
			return new Lesson();
		}
	}

	@Override
	public List<Lesson> findAll() {
		List<Lesson> lessons = this.lessonRepository.findAll();
		if(lessons.size() > 0) {
			return lessons;
		} else {
			return Collections.emptyList();
		}
	}

}
