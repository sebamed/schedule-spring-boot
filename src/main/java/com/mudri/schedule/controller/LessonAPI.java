/**
 * SCHEDULE API
 */
package com.mudri.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.mudri.schedule.utils.ReturnResponse;

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
		return ReturnResponse.listGet(this.lessonService.getAllDTO());
	}

	@PostMapping()
	public ResponseEntity<LessonDTO> handleCreateLesson(@RequestBody CreateLessonDTO lessonDTO) {
		return ReturnResponse.entityCreated(this.lessonService.create(lessonDTO));
	}

	@GetMapping("/{id}/students")
	public ResponseEntity<List<UserDTO>> handleGetStudents(@PathVariable("id") Long id) {
		return ReturnResponse.listGet(this.lessonService.getStudentsDTO(id));
	}

	@PostMapping("/confirm")
	public ResponseEntity<LessonDTO> handleConfirmLesson(@RequestBody UserLessonDTO userLessonDTO) {
		return ReturnResponse.entityCreated(this.lessonService.confirm(userLessonDTO));
	}

	@PostMapping("/join")
	public ResponseEntity<LessonDTO> handleJoinLesson(@RequestBody UserLessonDTO userLessonDTO) {
		return ReturnResponse.entityGet(this.lessonService.join(userLessonDTO));
	}

	@PostMapping("/leave")
	public ResponseEntity<LessonDTO> handleLeaveLesson(@RequestBody UserLessonDTO userLessonDTO) {
		return ReturnResponse.entityGet(this.lessonService.leave(userLessonDTO));
	}

}
