package com.pe.studynow.business.crud;

import java.util.List;

import com.pe.studynow.model.entity.Student;

public interface StudentService extends CrudService<Student, Integer>{
	List<Student> findByLastNameAndFirstName(String lastName, String firstName) throws Exception;

}
