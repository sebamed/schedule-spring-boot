/**
 * SCHEDULE API
 */
package com.mudri.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mudri.schedule.dto.CreateLessonDTO;
import com.mudri.schedule.dto.LessonDTO;
import com.mudri.schedule.dto.UserDTO;
import com.mudri.schedule.dto.UserLessonDTO;
import com.mudri.schedule.service.LessonService;

/*
  +---------------------------------------------+
  | Name: LessonAPI                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@RestController
@RequestMapping("/api/lessons")
public class LessonAPI {

	@Autowired
	LessonService lessonService;

	@GetMapping()
	public ResponseEntity<List<LessonDTO>> handleGetAllLessons() {
		List<LessonDTO> lessons = this.lessonService.getAllDTO();
	}

	@PostMapping()
	public ResponseEntity<LessonDTO> handleCreateLesson(@RequestBody CreateLessonDTO lessonDTO) {
		return new ResponseEntity<LessonDTO>(this.lessonService.create(lessonDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}/students")
	public ResponseEntity<List<UserDTO>> handleGetStudents(@PathVariable("id") Long id){
		List<UserDTO> users = this.lessonService.getStudentsDTO(id);
		return users.isEmpty()  ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	@PostMapping("/confirm")
	public ResponseEntity<LessonDTO> handleConfirmLesson(@RequestBody UserLessonDTO userLessonDTO) {
		return new ResponseEntity<LessonDTO>(this.lessonService.confirm(userLessonDTO), HttpStatus.OK);
	}

	@PostMapping("/join")
	public ResponseEntity<LessonDTO> handleJoinLesson(@RequestBody UserLessonDTO userLessonDTO) {
		return new ResponseEntity<LessonDTO>(this.lessonService.join(userLessonDTO), HttpStatus.OK);
	}

	@PostMapping("/leave")
	public ResponseEntity<LessonDTO> handleLeaveLesson(@RequestBody UserLessonDTO userLessonDTO) {
		return new ResponseEntity<LessonDTO>(this.lessonService.leave(userLessonDTO), HttpStatus.OK);
	}

}
