/**
 * SCHEDULE API
 */
package com.mudri.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mudri.schedule.consts.RoleConstants;
import com.mudri.schedule.dto.ConfirmLessonDTO;
import com.mudri.schedule.dto.CreateLessonDTO;
import com.mudri.schedule.dto.LessonDTO;
import com.mudri.schedule.dto.UpdateLessonDTO;
import com.mudri.schedule.dto.UserDTO;
import com.mudri.schedule.dto.UserLessonDTO;
import com.mudri.schedule.service.LessonService;
import com.mudri.schedule.utils.ReturnResponse;

/**
 * Endpoints for lessons
 * 
 * @author sebamed
 */
@RestController
@RequestMapping("/api/lessons")
public class LessonAPI {

	@Autowired
	LessonService lessonService;

	@GetMapping()
    @PreAuthorize(RoleConstants.AUTH_USER_ADMIN)
	public ResponseEntity<List<LessonDTO>> handleGetAllLessons() {
		return ReturnResponse.listGet(this.lessonService.getAllDTO());
	}

	@PostMapping()
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<LessonDTO> handleCreateLesson(@RequestBody CreateLessonDTO lessonDTO) {
		return ReturnResponse.entityCreated(this.lessonService.create(lessonDTO));
	}
	
	@GetMapping("/skill/{name}")
    @PreAuthorize(RoleConstants.AUTH_USER_ADMIN)
	public ResponseEntity<List<LessonDTO>> handleGetBySkillName(@PathVariable("name") String name) {
		return ReturnResponse.listGet(this.lessonService.getLessonsBySkillDTO(name));
	}
	
	@GetMapping("/confirmed/{id}")
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<List<LessonDTO>> handleGetAllConfirmedByTeacher(@PathVariable("id") Long id) {
		return ReturnResponse.listGet(this.lessonService.getConfirmedLessonsByUserId(id));
	}
	
	@GetMapping("/canceled/{id}")
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<List<LessonDTO>> handleGetAllCanceledByTeacher(@PathVariable("id") Long id) {
		return ReturnResponse.listGet(this.lessonService.getCanceledLessonsByUserId(id));
	}
	
	@GetMapping("/done/{id}")
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<List<LessonDTO>> handleGetAllDoneByTeacher(@PathVariable("id") Long id) {
		return ReturnResponse.listGet(this.lessonService.getDoneLessonsByUserId(id));
	}

	@GetMapping("/{id}/students")
    @PreAuthorize(RoleConstants.AUTH_USER_ADMIN)
	public ResponseEntity<List<UserDTO>> handleGetStudents(@PathVariable("id") Long id) {
		return ReturnResponse.listGet(this.lessonService.getStudentsDTO(id));
	}

	@PostMapping("/confirm")
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<LessonDTO> handleConfirmLesson(@RequestBody ConfirmLessonDTO confirmLessonDTO) {
		return ReturnResponse.entityCreated(this.lessonService.confirm(confirmLessonDTO));
	}

	@PostMapping("/join")
    @PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<LessonDTO> handleJoinLesson(@RequestBody UserLessonDTO userLessonDTO) {
		return ReturnResponse.entityGet(this.lessonService.join(userLessonDTO));
	}

	@PostMapping("/leave")
    @PreAuthorize(RoleConstants.AUTH_USER)
	public ResponseEntity<LessonDTO> handleLeaveLesson(@RequestBody UserLessonDTO userLessonDTO) {
		return ReturnResponse.entityGet(this.lessonService.leave(userLessonDTO));
	}
	
	@PostMapping("/cancel")
	@PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<LessonDTO> handleCancelLesson(@RequestBody UserLessonDTO userLessonDTO){
		return ReturnResponse.entityGet(this.lessonService.cancel(userLessonDTO));
	}
	
	@PostMapping("/update")
	@PreAuthorize(RoleConstants.AUTH_ADMIN)
	public ResponseEntity<LessonDTO> handleUpdateLesson(@RequestBody UpdateLessonDTO updateLessonDTO){
		return ReturnResponse.entityGet(this.lessonService.update(updateLessonDTO));
	}

}
