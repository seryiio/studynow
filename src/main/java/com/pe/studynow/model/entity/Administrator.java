package com.pe.studynow.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="administrators") 
public class Administrator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 1,max = 50, message = "El valor debe estar entre 1 y 50 caracteres")
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	
	@Size(min = 1,max = 50, message = "El valor debe estar entre 1 y 50 caracteres")
	@Column(name = "first_name", length = 50, nullable = false)	
	private String firstName;

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

}
