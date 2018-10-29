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
import com.mudri.schedule.dto.CourseDTO;
import com.mudri.schedule.dto.SubjectDTO;
import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.exception.SaveFailedException;
import com.mudri.schedule.model.Course;
import com.mudri.schedule.repository.CourseRepository;
import com.mudri.schedule.utils.TargetType;

/*
  +---------------------------------------------+
  | Name: CourseService                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Service
public class CourseService implements BaseCrudInterface<Course> {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	SubjectService subjectService;

	@Autowired
	ModelMapper modelMapper;

	public CourseDTO create(CourseDTO courseDTO) {
		Course course = this.modelMapper.map(courseDTO, Course.class);

		courseDTO.setId(this.save(course).getId());

		SubjectDTO subjectDTO = this.subjectService.getDTOById(course.getSubject().getId());

		courseDTO.getSubject().setName(subjectDTO.getName());
		return courseDTO;
	}

	public List<CourseDTO> getAllDTO() {
		return this.modelMapper.map(this.findAll(), TargetType.courseType);
	}

	public CourseDTO getDTOById(Long id) {
		return this.modelMapper.map(this.findOneById(id), CourseDTO.class);
	}

	@Override
	public Course save(Course object) {
		try {
			return this.courseRepository.save(object);
		} catch (Exception e) {
			throw new SaveFailedException("Course not saved! ID: " + object.getId());
		}
	}

	@Override
	public Course findOneById(Long id) {
		Optional<Course> course = this.courseRepository.findOneById(id);
		if (course.isPresent()) {
			return course.get();
		} else {
			throw new NotFoundException("No course with id:" + id);
		}
	}

	@Override
	public List<Course> findAll() {
		List<Course> courses = this.courseRepository.findAll();
		if (!courses.isEmpty()) {
			return courses;
		} else {
			return Collections.emptyList();
		}
	}

}
