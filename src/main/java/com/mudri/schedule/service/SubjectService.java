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
import com.mudri.schedule.dto.SubjectDTO;
import com.mudri.schedule.exception.EntityAlreadyExistsException;
import com.mudri.schedule.exception.NotFoundException;
import com.mudri.schedule.exception.SaveFailedException;
import com.mudri.schedule.model.Subject;
import com.mudri.schedule.repository.SubjectRepository;
import com.mudri.schedule.utils.TargetType;

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
	
	public SubjectDTO update(SubjectDTO subjectDTO) {
		Subject subject = this.findOneById(subjectDTO.getId());
		subject.setName(subjectDTO.getName());
		return this.modelMapper.map(this.save(subject), SubjectDTO.class);
	}

	public SubjectDTO create(SubjectDTO subjectDTO) {
		
		if(this.doesSubjectExist(subjectDTO.getName())) {
			throw new EntityAlreadyExistsException("Subject with name: " + subjectDTO.getName() + " already exists!");
		}
		
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
		return this.modelMapper.map(this.findAll(), TargetType.subjectType);
	}

	@Override
	public Subject save(Subject object) {
		try {
			return this.subjectRepository.save(object);
		} catch (Exception e) {
			throw new SaveFailedException("Subject not saved!");
		}
	}
	
	private boolean doesSubjectExist(String name) {
		return this.subjectRepository.findOneByName(name).isPresent();
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
