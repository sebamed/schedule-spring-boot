/**
 * SCHEDULE API
 */
package com.mudri.schedule.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mudri.schedule.model.Lesson;

/*
  +---------------------------------------------+
  | Name: LessonRepository                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>{

	Optional<Lesson> findOneById(Long id);
	List<Lesson> findAll();
	
}
