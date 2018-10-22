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
import com.mudri.schedule.dto.CreateLessonDTO;
import com.mudri.schedule.dto.LessonDTO;
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
	
	public LessonDTO create(CreateLessonDTO lessonDTO) {
		
		User user = this.userService.findOneById(lessonDTO.getUserID());
		if(user.getId() != null) {
			Lesson lesson = new Lesson();
			lesson.getStudents().add(user);
			lesson.setConfirmed(false);
			lesson.setLength(0);
			lesson.setPrice(0);
			lesson.setTeacher(null);
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
		List<LessonDTO> lessonsDTO = new ArrayList<>();
		
		for(Lesson lesson : this.findAll()) {
			lessonsDTO.add(this.modelMapper.map(lesson, LessonDTO.class));
		}
		
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
