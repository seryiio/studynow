package com.pe.studynow.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teachers", 
	indexes = {@Index(columnList = "last_name, first_name", name = "teachers_index_last_name_first_name")})
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 1,max = 50, message = "El valor debe estar entre 1 y 50 caracteres")
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	
	@Size(min = 1,max = 50, message = "El valor debe estar entre 1 y 50 caracteres")
	@Column(name = "first_name", length = 50, nullable = false)	
	private String firstName;
	
	@Min(value = 1, message = "El valor debe ser mayor a 1")
	@Max(value = 70, message = "El valor debe menor a 70")
	@Column(name = "age")
	private Integer age;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	@ManyToOne
	@JoinColumn(name = "career_id")
	private Career career;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}
	
	
}
