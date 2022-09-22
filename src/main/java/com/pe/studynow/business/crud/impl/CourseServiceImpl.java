package com.pe.studynow.business.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.studynow.business.crud.CourseService;
import com.pe.studynow.model.entity.Course;
import com.pe.studynow.model.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public JpaRepository<Course, Integer> getJpaRepository() {
		// TODO Auto-generated method stub
		return  this.courseRepository;
	}

	@Override
	@Transactional
	public Integer insert(Course course) {
		int rpta = courseRepository.buscarNombreCurso(course.getName());
		if (rpta == 0) {
			courseRepository.save(course);
		}
		return rpta;
	}

	@Override
	@Transactional
	public Integer deleteCourses(Integer id) {
		int rpta = courseRepository.eliminarMuchosCurso(id);
		return rpta;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> list() {
		// TODO Auto-generated method stub
		return courseRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
	}

}
