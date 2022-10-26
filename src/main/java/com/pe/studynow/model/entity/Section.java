package com.pe.studynow.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sections")
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 1,max = 7, message = "El valor debe estar entre 1 y 7 caracteres")
	@Column(name = "name", length = 7)
	private String name;
	
	@Max(value = 10, message = "El valor debe ser menor a 10")
	@Column(name = "vacancies")
	private int vacancies;

	@NotEmpty(message = "Debe ingresar un la hora de inicio")
	@Column(name = "start_time")
	private String startTime;

	@NotEmpty(message = "Debe ingresar un la hora de fin")
	@Column(name = "end_time")
	private String endTime;

	@NotEmpty(message = "Debe ingresar un dia")
	@Column(name = "days")
	private String day;

	@OneToMany(mappedBy = "section")
	private List<Enrollment> enrollment;
	
	@ManyToOne
	@NotNull(message = "Debe ingresar un Curso")
	@JoinColumn(name = "course_id")
	private Course course;

	@ManyToOne
	@NotNull(message = "Debe ingresar un Profesor")
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;
	
	public Section() {
		enrollment = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVacancies() {
		return vacancies;
	}

	public void setVacancies(int vacancies) {
		this.vacancies = vacancies;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Enrollment> getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(List<Enrollment> enrollment) {
		this.enrollment = enrollment;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	
}
