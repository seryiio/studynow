package com.pe.studynow.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.studynow.model.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{
	@Query("select count(s.firstName) from Student s where s.firstName =:firstName")
	public int buscarNombreEstudiante(@Param("firstName") String nombreEstudiante);
}
