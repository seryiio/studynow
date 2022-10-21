package com.pe.studynow.business.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.studynow.business.crud.TeacherService;
import com.pe.studynow.model.entity.Teacher;
import com.pe.studynow.model.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{


	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public JpaRepository<Teacher, String> getJpaRepository() {
		return this.teacherRepository;
	}


	@Override
	@Transactional
	public Integer insert(Teacher teacher) {
		int rpta = teacherRepository.buscarNombreProfesor(teacher.getFirstName());
		if (rpta == 0) {
			teacherRepository.save(teacher);
		}
		return rpta;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Teacher> list() {
		// TODO Auto-generated method stub
		return teacherRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName"));
	}

}
