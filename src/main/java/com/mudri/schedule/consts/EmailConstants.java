/**
 * SCHEDULE API
 */
package com.mudri.schedule.consts;

/**
 * Constants for email service
 * @author sebamed
 */

public final class EmailConstants {

	private final static String FILE_PREFIX = "mails/";
	private final static String CONFIRM_PREFIX = "confirm/";
	private final static String CANCEL_PREFIX = "cancel/";
	private final static String UPDATE_PREFIX = "update/";
	
	public final static String SUBJECT_LESSON_CONFIRMED_STUDENT = "The lesson you want to attend is confirmed";
	public final static String SUBJECT_LESSON_CONFIRMED_TEACHER = "Lesson successfully confirmed";
	public final static String SUBJECT_LESSON_CANCELED_STUDENT = "The lesson you joined is canceled";
	public final static String SUBJECT_LESSON_CANCELED_TEACHER = "Lesson successfully canceled";
	public final static String SUBJECT_LESSON_UPDATED_STUDENT = "The lesson you joined is updated";
	public final static String SUBJECT_LESSON_UPDATED_TEACHER = "Lesson successfully updated";
	
	
	public final static String FILE_LESSON_CONFIRMED_STUDENT = FILE_PREFIX + CONFIRM_PREFIX +"lesson-confirmed-student.html";
	public final static String FILE_LESSON_CONFIRMED_TEACHER = FILE_PREFIX + CONFIRM_PREFIX + "lesson-confirmed-teacher.html";
	public final static String FILE_LESSON_CANCELED_STUDENT = FILE_PREFIX +  CANCEL_PREFIX + "lesson-canceled-student.html";
	public final static String FILE_LESSON_CANCELED_TEACHER = FILE_PREFIX + CANCEL_PREFIX + "lesson-canceled-teacher.html";
	public final static String FILE_LESSON_UPDATED_STUDENT = FILE_PREFIX +  UPDATE_PREFIX + "lesson-updated-student.html";
	public final static String FILE_LESSON_UPDATED_TEACHER = FILE_PREFIX + UPDATE_PREFIX + "lesson-updated-teacher.html";
	
	
}
