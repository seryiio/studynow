package com.pe.studynow.business.crud;

import java.util.List;

import com.pe.studynow.model.entity.Teacher;

public interface TeacherService extends CrudService<Teacher, Integer>{
	List<Teacher> findByLastNameAndFirstName(String lastName, String firstName) throws Exception;

}
