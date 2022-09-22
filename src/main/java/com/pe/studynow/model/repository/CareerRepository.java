package com.pe.studynow.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.studynow.model.entity.Career;

@Repository
public interface CareerRepository extends JpaRepository<Career, Integer>{
	@Query("select count(c.name) from Career c where c.name =:name")
	public int buscarNombreCarrera(@Param("name") String nombreCarrera);

}
