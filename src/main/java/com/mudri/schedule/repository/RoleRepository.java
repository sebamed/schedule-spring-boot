/**
 * SCHEDULE API
 */
package com.mudri.schedule.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mudri.schedule.model.Role;

/*
  +---------------------------------------------+
  | Name: RoleRepository                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findOneById(Long id);
	Optional<Role> findOneByName(String name);
	List<Role> findAll();
	
}
