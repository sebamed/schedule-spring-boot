/**
 * SCHEDULE API
 */
package com.mudri.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  +---------------------------------------------+
  | Name: CreateLessonDTO                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLessonDTO {

	// TODO: dodati datum i vreme
	
	private Long userID;
	private CourseDTO course;
	
}
