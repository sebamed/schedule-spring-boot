/**
 * SCHEDULE API
 */
package com.mudri.schedule.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mudri.schedule.model.User;

/*
  +---------------------------------------------+
  | Name: UserRepository                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findOneById(Long id);
	Optional<User> findOneByEmail(String email);
	List<User> findAllByRoleId(Long id);
	List<User> findAllByRoleName(String name);
	List<User> findAll();
	List<User> findAllBySkillsId(Long id);
	
}
