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
  | @author sebamed                         
  | Date: Oct 30, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmLessonDTO {

	private Long userId;
	private Long lessonId;
	private int length;
	private int price;
	
}
