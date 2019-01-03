/**
 * SCHEDULE API
 */
package com.mudri.schedule.utils;

import com.mudri.schedule.dto.RegisterDTO;
import com.mudri.schedule.dto.UpdateLessonDTO;
import com.mudri.schedule.model.Lesson;
import com.mudri.schedule.model.User;

/**
 * contains methods for updating models with dto data
 * 
 * @author sebamed
 */

public class ModelUpdater {

	/**
	 * called when creating a new user
	 * @param user
	 * @param registerDTO
	 * @return
	 */
	public static User registerUser(User user, RegisterDTO registerDTO) {
		user.setEmail(registerDTO.getEmail());
		user.setFirstName(registerDTO.getFirstName());
		user.setLastName(registerDTO.getLastName());
		
		return user;
	}

	/**
	 * called when updating an existing lesson
	 * @param lesson
	 * @param lessonDTO
	 * @return
	 */
	public static Lesson updateLesson(Lesson lesson, UpdateLessonDTO lessonDTO) {
		lesson.setDate(lessonDTO.getDate());
		lesson.setTime(lessonDTO.getTime());
		lesson.setLength(lessonDTO.getLength());
		lesson.setPrice(lessonDTO.getPrice());
		
		return lesson;
	}

}
