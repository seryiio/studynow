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
	
	@Min(value = 10000000, message = "El valor debe tener 8 digitos")
	@Max(value = 99999999, message = "El valor debe tener 8 digitos")
	@Column(name = "dni")	
	private Integer dni;
	
	@Min(value = 100000000, message = "El valor debe tener 9 digitos")
	@Max(value = 999999999, message = "El valor debe tener 9 digitos")
	@Column(name = "phone_number")
	private Integer phoneNumber;

	@Size(min = 1,max = 70, message = "El valor debe estar entre 1 y 70 caracteres")
	@Column(name = "email_university", length = 70)	
	private String emailUniversity;

	@Size(min = 1,max = 70, message = "El valor debe estar entre 1 y 70 caracteres")
	@Column(name = "email_personal", length = 70)	
	private String emailPersonal;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Section section;

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

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailUniversity() {
		return emailUniversity;
	}

	public void setEmailUniversity(String emailUniversity) {
		this.emailUniversity = emailUniversity;
	}

	public String getEmailPersonal() {
		return emailPersonal;
	}

	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}
	
	
}
