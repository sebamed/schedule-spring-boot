/**
 * SCHEDULE API
 */
package com.mudri.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  +---------------------------------------------+
  | Name: LoginDTO                                  
  | Author: Sebastian                         
  | Date: Oct 28, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

	private String email;
	private String password;
	
}
