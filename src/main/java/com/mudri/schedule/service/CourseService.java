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
import com.mudri.schedule.dto.CourseDTO;
import com.mudri.schedule.dto.SubjectDTO;
import com.mudri.schedule.model.Course;
import com.mudri.schedule.repository.CourseRepository;

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
		if (subjectDTO.getId() != null) {
			courseDTO.getSubject().setName(subjectDTO.getName());
			return courseDTO;
		} else {
			return new CourseDTO();
		}
	}

	public List<CourseDTO> getAllDTO() {
		List<CourseDTO> coursesDTO = new ArrayList<>();

		for (Course course : this.findAll()) {
			coursesDTO.add(this.modelMapper.map(course, CourseDTO.class));
		}

		return coursesDTO;
	}

	public CourseDTO getDTOById(Long id) {
		Course course = this.findOneById(id);
		if (course.getId() != null) {
			return this.modelMapper.map(course, CourseDTO.class);
		} else {
			return new CourseDTO();
		}
	}

	@Override
	public Course save(Course object) {
		return this.courseRepository.save(object);
	}

	@Override
	public Course findOneById(Long id) {
		Optional<Course> course = this.courseRepository.findOneById(id);
		if (course.isPresent()) {
			return course.get();
		} else {
			return new Course();
		}
	}

	@Override
	public List<Course> findAll() {
		List<Course> courses = this.courseRepository.findAll();
		if (courses.size() > 0) {
			return courses;
		} else {
			return Collections.emptyList();
		}
	}

}
