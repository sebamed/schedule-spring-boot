/**
 * SCHEDULE API
 */
package com.mudri.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  +---------------------------------------------+
  | Name: ConfirmLessonDTO                                  
  | Author: Sebastian                         
  | Date: Oct 25, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLessonDTO {

	private Long lessonId;
	private Long userId;
	
}
