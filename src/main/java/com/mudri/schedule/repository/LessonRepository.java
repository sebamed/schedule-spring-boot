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

	/**
	 * returns all non confirmed lessons by skill name
	 * @param name
	 * @return
	 */
	@Query(nativeQuery = true, value = "select * from lesson l inner join course c on l.course_id = c.id inner join subject s on c.subject_id = s.id where s.name = ?1 and l.confirmed = 0 and l.canceled = 0")
	List<Lesson> findAllNonConfirmedBySkillName(String name);
	
	/**
	 * returns all confirmed lessons for one teacher that are not yet done and not canceled.
	 * @param id
	 * @return
	 */
	@Query(nativeQuery = true, value = "select * from lesson l inner join user u on l.teacher_id = u.id where u.id = ?1 and l.confirmed = 1 and l.date <= CURDATE() and l.canceled = 0")
	List<Lesson> findAllConfirmedByUserId(Long id);
	
	/**
	 * returns all canceled lessons for teacher
	 * @param id
	 * @return
	 */
	@Query(nativeQuery = true, value = "select * from lesson l inner join user u on l.teacher_id = u.id where u.id = ?1 and l.canceled = 1")
	List<Lesson> findAllCanceledByUserId(Long id);
	
	@Query(nativeQuery = true, value = "select * from lesson l inner join user u on l.teacher_id = u.id where u.id = ?1 and l.canceled = 0 and l.confirmed = 1 and l.date < CURDATE()")
	List<Lesson> findAllDoneByUser(Long id);

}
