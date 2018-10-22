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
  | Name: LessonDTO                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDTO {

	private Long id;
	private boolean confirmed;
	private int price;
	private int length;
	private List<UserDTO> students;
	private UserDTO teacher;
	private CourseDTO course;
	
}
