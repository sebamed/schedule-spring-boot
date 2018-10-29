/**
 * SCHEDULE API
 */
package com.mudri.schedule.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
public interface LessonRepository extends JpaRepository<Lesson, Long> {

	Optional<Lesson> findOneById(Long id);

	List<Lesson> findAll();

	@Query(nativeQuery = true, value = "select * from lesson l inner join course c on l.course_id = c.id inner join subject s on c.subject_id = s.id where s.name = ?1")
	List<Lesson> findAllBySkillName(String name);

}
