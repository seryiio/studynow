package com.pe.studynow.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.studynow.model.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	@Query("select count(c.name) from Course c where c.name =:name")
	public int buscarNombreProfesor(@Param("name") String nombreProfesor);
}
