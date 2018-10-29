/**
 * SCHEDULE API
 */
package com.mudri.schedule.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mudri.schedule.model.AppUser;

/*
  +---------------------------------------------+
  | Name: UserRepository                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

	Optional<AppUser> findOneById(Long id);
	Optional<AppUser> findOneByEmail(String email);
	List<AppUser> findAllByRoleId(Long id);
	List<AppUser> findAllByRoleName(String name);
	List<AppUser> findAll();
	
}
