/**
 * SCHEDULE API
 */
package com.mudri.schedule.dto;

import java.sql.Date;
import java.sql.Time;
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
	private int price;
	private int length;
	private boolean canceled;
	private boolean confirmed;
	private Date date;
	private Time time;
	private UserDTO teacher;
	private List<UserDTO> students;
	private CourseDTO course;
	
}
