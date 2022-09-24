package com.pe.studynow.business.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.pe.studynow.business.crud.TeacherService;
import com.pe.studynow.model.entity.Teacher;
import com.pe.studynow.model.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{


	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public JpaRepository<Teacher, Integer> getJpaRepository() {
		return this.teacherRepository;
	}

	@Override
	public List<Teacher> findByLastNameAndFirstName(String lastName, String firstName) throws Exception {
		return this.teacherRepository.findByLastNameAndFirstName(lastName, firstName);
	}
}
