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
import com.mudri.schedule.dto.SubjectDTO;
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
		
		if(subjectDTO.getName().isEmpty()) {
			return new SubjectDTO();
		}
		
		Subject subject = new Subject();
		subject.setName(subjectDTO.getName());
		
		subjectDTO.setId(this.save(subject).getId());
		
		return subjectDTO;
	}
	
	public SubjectDTO getDTOById(Long id) {
		Subject subject = this.findOneById(id);
		if (subject.getId() != null) {
			return this.modelMapper.map(subject, SubjectDTO.class);
		} else {
			return new SubjectDTO();
		}
	}
	
	public List<SubjectDTO> getAllDTO(){
		List<SubjectDTO> subjectsDTO = new ArrayList<>();
		
		for(Subject subject : this.findAll()) {
			subjectsDTO.add(this.modelMapper.map(subject, SubjectDTO.class));
		}
		
		return subjectsDTO;
	}

	@Override
	public Subject save(Subject object) {
		return this.subjectRepository.save(object);
	}

	@Override
	public Subject findOneById(Long id) {
		Optional<Subject> optional = this.subjectRepository.findOneById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return new Subject();
		}
	}

	@Override
	public List<Subject> findAll() {
		List<Subject> subjects = this.subjectRepository.findAll();
		if (subjects.size() > 0) {
			return subjects;
		} else {
			return Collections.emptyList();
		}
	}

}
