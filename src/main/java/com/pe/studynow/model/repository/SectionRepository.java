package com.pe.studynow.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.studynow.model.entity.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer>{
	@Query("select count(c.name) from Section c where c.name =:name")
	public int buscarNombreSeccion(@Param("name") String nombreSeccion);
	
	@Query(value="delete from sections where id IN (=:secciones)", nativeQuery = true)
	public int eliminarMuchasSecciones(@Param("secciones") Integer nombreSeccionEliminar);

}
