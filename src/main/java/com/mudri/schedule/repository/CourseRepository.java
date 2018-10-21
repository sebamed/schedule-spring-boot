/**
 * SCHEDULE API
 */
package com.mudri.schedule.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mudri.schedule.model.Course;

/*
  +---------------------------------------------+
  | Name: CourseRepository                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findOneById(Long id);
	List<Course> findAll();
	
}
