package com.pe.studynow.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.studynow.model.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{
	@Query("select count(c.name) from Course c where c.name =:name")
	public int buscarNombreCurso(@Param("name") String nombreCurso);
	
	@Query(value="delete from courses where id IN (=:cursos)", nativeQuery = true)
	public int eliminarMuchosCurso(@Param("cursos") Integer nombreCursosEliminar);

}
