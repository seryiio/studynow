package com.pe.studynow.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.studynow.model.entity.Section;
import com.pe.studynow.model.entity.Teacher;
import com.pe.studynow.model.entity.Course;


@Repository
public interface SectionRepository extends JpaRepository<Section, Integer>{
	@Query("select count(c.name) from Section c where c.name =:name")
	public int buscarNombreSeccion(@Param("name") String nombreSeccion);
	
	@Query(value="delete from sections where id IN (=:secciones)", nativeQuery = true)
	public int eliminarMuchasSecciones(@Param("secciones") Integer nombreSeccionEliminar);

	List<Section> findByTeacher(Teacher teacher) throws Exception;
	List<Section> findByCourse(Course course) throws Exception;

}
