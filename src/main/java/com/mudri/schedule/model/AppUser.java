/**
 * SCHEDULE API
 */
package com.mudri.schedule.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mudri.schedule.dto.RegisterDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  +---------------------------------------------+
  | Name: User                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToMany(mappedBy = "teacher", cascade = { CascadeType.REFRESH })
	private List<Lesson> teachedLessons;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST }, mappedBy = "students")
	private List<Lesson> lessons;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "user_skills", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "subject_id") })
	private List<Subject> skills;

	public void setFieldsFromRegisterDTO(RegisterDTO registerDTO) {
		this.setEmail(registerDTO.getEmail());
		this.setFirstName(registerDTO.getFirstName());
		this.setLastName(registerDTO.getLastName());
	}
}
