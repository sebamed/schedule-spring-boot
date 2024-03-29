/**
 * SCHEDULE API
 */
package com.mudri.schedule.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  +---------------------------------------------+
  | Name: UserDTO                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private RoleDTO role;	
	private List<SubjectDTO> skills;
	
	@JsonBackReference
	private List<LessonDTO> teachedLessons;
	
	@JsonBackReference
	private List<LessonDTO> lessons;

}
