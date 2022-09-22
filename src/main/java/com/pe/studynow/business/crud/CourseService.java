package com.pe.studynow.business.crud;

import java.util.List;

import com.pe.studynow.model.entity.Course;

public interface CourseService extends CrudService<Course, Integer>{
	public Integer insert(Course course);
	
	public Integer deleteCourses(Integer id);
	
	List<Course> list();
}
