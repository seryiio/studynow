package com.pe.studynow.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="enrollments") 
//indexes = {@Index(columnList = "type", name = "enrollments_index_type")},uniqueConstraints = @UniqueConstraint(columnNames = {"section_id"}))
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enrollment_id")
	private Integer id;
	
	@Column(name = "type", length = 35)
	private String type;
	
	@Column(name = "number_cycle", nullable = false)
	private String numberCycle;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumberCycle() {
		return numberCycle;
	}

	public void setNumberCycle(String numberCycle) {
		this.numberCycle = numberCycle;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
