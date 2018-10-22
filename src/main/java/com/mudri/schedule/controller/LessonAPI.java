/**
 * SCHEDULE API
 */
package com.mudri.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mudri.schedule.dto.CreateLessonDTO;
import com.mudri.schedule.dto.LessonDTO;
import com.mudri.schedule.service.LessonService;

/*
  +---------------------------------------------+
  | Name: LessonAPI                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@RestController
@RequestMapping("/api/lesson")
public class LessonAPI {

	@Autowired
	LessonService lessonService;

	@GetMapping("/")
	public ResponseEntity<List<LessonDTO>> handleGetAllLessons() {
		List<LessonDTO> lessonsDTO = this.lessonService.getAllDTO();
		if (!lessonsDTO.isEmpty()) {
			return new ResponseEntity<List<LessonDTO>>(lessonsDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// todo confirm lesson...

	@PostMapping("/")
	public ResponseEntity<LessonDTO> handleCreateLesson(@RequestBody CreateLessonDTO lessonDTO) {
		LessonDTO newLessonDTO = this.lessonService.create(lessonDTO);
		if(newLessonDTO.getId() != null) {
			return new ResponseEntity<LessonDTO>(newLessonDTO, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
