package com.pe.studynow.business.crud;

import java.util.List;

import com.pe.studynow.model.entity.Teacher;

public interface TeacherService extends CrudService<Teacher, String>{
	public Integer insert(Teacher teacher);
	
	List<Teacher> list();

}
