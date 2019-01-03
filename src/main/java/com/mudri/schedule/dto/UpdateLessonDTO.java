/**
 * SCHEDULE API
 */
package com.mudri.schedule.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for updating lesson
 * @author sebamed
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLessonDTO {

	private Long userId;
	private Long lessonId;
	private int length;
	private int price;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private Date time;
	
}
