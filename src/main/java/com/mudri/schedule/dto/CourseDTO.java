/**
 * SCHEDULE API
 */
package com.mudri.schedule.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  +---------------------------------------------+
  | Name: CourseDTO                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
	
	private Long id;
	private List<String> types;
	private List<String> material;
	private SubjectDTO subject;

}
