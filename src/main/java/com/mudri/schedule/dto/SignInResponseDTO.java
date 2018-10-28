/**
 * SCHEDULE API
 */
package com.mudri.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  +---------------------------------------------+
  | Name: SignInResponseDTO                                  
  | Author: Sebastian                         
  | Date: Oct 28, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDTO {

	private UserInfoDTO user;
	private String token;
	
}
