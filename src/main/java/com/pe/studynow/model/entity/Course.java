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
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 1,max = 75, message = "El valor debe estar entre 1 y 75 digitos")
	@Column(name = "name", length = 75, nullable = false)
	private String name;
	
	@Min(value = 1, message = "El valor debe ser mayor a 1 y menor a 8")
	@Max(value = 8, message = "El valor debe ser mayor a 1 y menor a 8")
	@Column(name = "number_credits")
	private int numberCredits;
	
	@OneToMany(mappedBy = "course")
	private List<Section> section;

	@ManyToOne
	@JoinColumn(name = "career_id")
	private Career career;
	
	public Course() {
		section = new ArrayList<>();
	}

	public Integer getId() {
		return id;
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

	public int getNumberCredits() {
		return numberCredits;
	}

	public void setNumberCredits(int numberCredits) {
		this.numberCredits = numberCredits;
	}

	public List<Section> getSection() {
		return section;
	}

	public void setSection(List<Section> section) {
		this.section = section;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	
}
