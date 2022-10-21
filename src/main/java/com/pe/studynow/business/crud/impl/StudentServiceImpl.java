package com.pe.studynow.business.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.studynow.business.crud.StudentService;
import com.pe.studynow.model.entity.Student;
import com.pe.studynow.model.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public JpaRepository<Student, String> getJpaRepository() {
		return this.studentRepository;
	}


	@Override
	@Transactional
	public Integer insert(Student student) {
		int rpta = studentRepository.buscarDNIEstudiante(student.getDni());
		if (rpta == 0) {
			studentRepository.save(student);
		}
		return rpta;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Student> list() {
		// TODO Auto-generated method stub
		return studentRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
	}
}
