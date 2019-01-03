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
  | Name: UserInfoDTO                                  
  | Author: Sebastian                         
  | Date: Oct 27, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private List<SubjectDTO> skills;
	private RoleDTO role;
	
}
