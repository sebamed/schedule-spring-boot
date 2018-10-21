/**
 * SCHEDULE API
 */
package com.mudri.schedule.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mudri.schedule.model.Subject;

/*
  +---------------------------------------------+
  | Name: SubjectRepository                                  
  | Author: Sebastian                         
  | Date: Oct 21, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	
	List<Subject> findAll();
	Optional<Subject> findOneById(Long id);
	Optional<Subject> findOneByName(String name);
	
}
