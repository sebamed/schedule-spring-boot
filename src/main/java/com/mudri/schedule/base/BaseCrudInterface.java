/**
 * SCHEDULE API
 */
package com.mudri.schedule.base;

import java.util.List;

/*
  +---------------------------------------------+
  | Name: BaseCrudInterface                                  
  | Author: Sebastian                         
  | Date: Oct 21, 2018                                                                                                                         
  +---------------------------------------------+
*/

public interface BaseCrudInterface<T> {
	
	T save(T object);
	
	T findOneById(Long id);
	
	List<T> findAll();
	
}
