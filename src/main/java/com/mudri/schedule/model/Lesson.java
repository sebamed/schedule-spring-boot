/**
 * SCHEDULE API
 */
package com.mudri.schedule.model;

import java.util.ArrayList;
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
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
  +---------------------------------------------+
  | Name: Lesson                                  
  | Author: Sebastian                         
  | Date: Oct 22, 2018                                                                                                                         
  +---------------------------------------------+
*/

@Entity
@Table(name = "lesson")
@Data
@AllArgsConstructor
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "confirmed")
	private boolean confirmed;

	@Column(name = "price_per_student")
	private int price;

	@Column(name = "length_in_minutes")
	private int length;

	// date time start separated

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	@JoinTable(name = "lesson_students", joinColumns = { @JoinColumn(name = "lesson_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private List<User> students;

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "teacher_id")
	private User teacher;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "course_id")
	private Course course;
	
	public Lesson() {
		this.students = new ArrayList<>();
		this.length = 0;
		this.price = 0;
		this.confirmed = false;
	}

}
