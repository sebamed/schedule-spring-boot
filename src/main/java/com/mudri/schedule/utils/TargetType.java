/**
 * SCHEDULE API
 */
package com.mudri.schedule.utils;

import java.lang.reflect.Type;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.mudri.schedule.dto.CourseDTO;
import com.mudri.schedule.dto.LessonDTO;
import com.mudri.schedule.dto.RoleDTO;
import com.mudri.schedule.dto.SubjectDTO;
import com.mudri.schedule.dto.UserDTO;
import com.mudri.schedule.dto.UserInfoDTO;

/*
  +---------------------------------------------+
  | Name: TargetTypes                                  
  | Author: Sebastian                         
  | Date: Oct 26, 2018                                                                                                                         
  +---------------------------------------------+
*/

public final class TargetType {

	public final static Type userType = new TypeToken<List<UserDTO>>() {
		private static final long serialVersionUID = 7984774601232761011L;
	}.getType();

	public final static Type userInfoType = new TypeToken<List<UserInfoDTO>>() {
		private static final long serialVersionUID = -3895670399509757735L;
	}.getType();

	public final static Type lessonType = new TypeToken<List<LessonDTO>>() {
		private static final long serialVersionUID = 7866099546453320304L;
	}.getType();

	public final static Type roleType = new TypeToken<List<RoleDTO>>() {
		private static final long serialVersionUID = 2833475874335703361L;
	}.getType();

	public final static Type subjectType = new TypeToken<List<SubjectDTO>>() {
		private static final long serialVersionUID = -8571365055777930843L;
	}.getType();

	public final static Type courseType = new TypeToken<List<CourseDTO>>() {
		private static final long serialVersionUID = -8908054437585662184L;
	}.getType();

}
