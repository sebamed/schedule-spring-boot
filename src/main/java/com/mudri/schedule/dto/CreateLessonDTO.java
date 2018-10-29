/**
 * SCHEDULE API
 */
package com.mudri.schedule.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private Date time;
	
}
