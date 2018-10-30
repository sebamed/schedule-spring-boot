/**
 * SCHEDULE API
 */
package com.mudri.schedule.base;

import java.util.List;

/**
 * Base interface with crud operations
 * @author sebamed
 */
public interface BaseCrudInterface<T> {
	
	T save(T object);
	
	T findOneById(Long id);
	
	List<T> findAll();
	
}
