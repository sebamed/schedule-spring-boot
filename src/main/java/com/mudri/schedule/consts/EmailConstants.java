/**
 * SCHEDULE API
 */
package com.mudri.schedule.consts;

/**
 * Constants for email service
 * @author sebamed
 */

public final class EmailConstants {

	public final static String SUBJECT_LESSON_CONFIRMED_STUDENT = "The lesson you want to attend is confirmed";
	public final static String SUBJECT_LESSON_CONFIRMED_TEACHER = "Lesson successfully confirmed";
	
	private final static String FILE_PREFIX = "mails/";
	
	public final static String FILE_LESSON_CONFIRMED_STUDENT = FILE_PREFIX + "lesson-confirmed-student.html";
	public final static String FILE_LESSON_CONFIRMED_TEACHER = FILE_PREFIX + "lesson-confirmed-teacher.html";
	
	
}
