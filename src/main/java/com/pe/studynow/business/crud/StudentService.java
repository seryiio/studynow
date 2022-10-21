package com.pe.studynow.business.crud;

import java.util.List;

import com.pe.studynow.model.entity.Student;

public interface StudentService extends CrudService<Student, String>{
	public Integer insert(Student student);
	
	List<Student> list();

}
