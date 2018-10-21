/**
 * SCHEDULE API
 */
package com.mudri.schedule.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/*
  +---------------------------------------------+
  | Name: ObjectMapper                                  
  | Author: Sebastian                         
  | Date: Oct 21, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Configuration
public class ObjectMapper {

	@Autowired
	ModelMapper modelMapper;
	
}
