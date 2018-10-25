/**
 * SCHEDULE API
 */
package com.mudri.schedule.service;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.mudri.schedule.base.BaseCrudInterface;
import com.mudri.schedule.dto.SubjectDTO;
import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.exception.SaveFailedException;
import com.mudri.schedule.model.Subject;
import com.mudri.schedule.repository.SubjectRepository;

/*
  +---------------------------------------------+
  | Name: SubjectService                                  
  | Author: Sebastian                         
  | Date: Oct 21, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Service
public class SubjectService implements BaseCrudInterface<Subject> {

	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	ModelMapper modelMapper;

	public SubjectDTO create(SubjectDTO subjectDTO) {
		Subject subject = new Subject();
		subject.setName(subjectDTO.getName());

		subjectDTO.setId(this.save(subject).getId());

		return subjectDTO;
	}

	public SubjectDTO getDTOById(Long id) {
		Subject subject = this.findOneById(id);

		return this.modelMapper.map(subject, SubjectDTO.class);
	}

	public List<SubjectDTO> getAllDTO() {
		Type targetSubjectType = new TypeToken<List<SubjectDTO>>() {
		}.getType();

		return this.modelMapper.map(this.findAll(), targetSubjectType);
	}

	@Override
	public Subject save(Subject object) {
		try {
			return this.subjectRepository.save(object);
		} catch (Exception e) {
			throw new SaveFailedException("Subject not saved!");
		}
	}

	@Override
	public Subject findOneById(Long id) {
		Optional<Subject> optional = this.subjectRepository.findOneById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new NotFoundException("No subject found with ID: " + id);
		}
	}

	@Override
	public List<Subject> findAll() {
		List<Subject> subjects = this.subjectRepository.findAll();
		if (!subjects.isEmpty()) {
			return subjects;
		} else {
			return Collections.emptyList();
		}
	}

}
