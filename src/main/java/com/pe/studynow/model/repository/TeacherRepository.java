package com.pe.studynow.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.studynow.model.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String>{
	@Query("select count(t.firstName) from Teacher t where t.firstName =:firstName")
	public int buscarNombreProfesor(@Param("firstName") String nombreProfesor);
}
