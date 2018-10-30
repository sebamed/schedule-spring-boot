/**
 * SCHEDULE API
 */
package com.mudri.schedule.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mudri.schedule.config.ApplicationProperties;
import com.mudri.schedule.consts.EmailConstants;
import com.mudri.schedule.dto.LessonDTO;
import com.mudri.schedule.dto.UserDTO;
import com.mudri.schedule.exception.EmailNotSentException;

/**
 * Service for sending mails
 * 
 * @author sebamed
 */

@Service
@Async
public class EmailService {

	@Autowired
	JavaMailSender javaMailSender;

	@Autowired
	TemplateEngine templateEngine;

	@Autowired
	MailProperties mailProperties;

	@Autowired
	ApplicationProperties applicationProperties;

	public void sendLessonConfirmedMail(LessonDTO lesson) {

		this.createAndSendMail(lesson, EmailConstants.SUBJECT_LESSON_CONFIRMED_STUDENT,
				EmailConstants.FILE_LESSON_CONFIRMED_STUDENT, this.formatMultipleEmails(lesson));

		this.createAndSendMail(lesson, EmailConstants.SUBJECT_LESSON_CONFIRMED_TEACHER,
				EmailConstants.FILE_LESSON_CONFIRMED_TEACHER, new String[] { lesson.getTeacher().getEmail() });
	}

	/**
	 * This method creates mail that will contain lesson information
	 * 
	 * @author sebamed
	 * @param lesson
	 * @param subject
	 * @param file
	 * @param mails
	 */
	private void createAndSendMail(LessonDTO lesson, String subject, String file, String[] mails) {
		final Context studentsContext = new Context();
		studentsContext.setVariable("lesson", lesson);
		this.sendHtmlMail(mails, subject, file, studentsContext);
	}

	/**
	 * This method sends an email
	 * 
	 * @author sebamed
	 * @param to
	 * @param subject
	 * @param templateName
	 * @param context
	 */
	private void sendHtmlMail(String[] to, String subject, String templateName, Context context) {
		MimeMessage mail = javaMailSender.createMimeMessage();

		String body = templateEngine.process(templateName, context);

		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mail, true);
			helper.setFrom(this.applicationProperties.getMail().getFrom());
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);
		} catch (MessagingException e) {
			throw new EmailNotSentException("E-mails were not sent!");
		}

		javaMailSender.send(mail);
	}

	private String[] formatMultipleEmails(LessonDTO lesson) {
		List<String> mails = new ArrayList<>();
		for (UserDTO user : lesson.getStudents()) {
			mails.add(user.getEmail());
		}

		String[] mailsArray = new String[mails.size()];

		return mails.toArray(mailsArray);
	}
}
