package com.pe.studynow.business.crud;

import java.util.List;

import com.pe.studynow.model.entity.Section;

public interface SectionService extends CrudService<Section, Integer>{
	public Integer insert(Section section);
	
	public Integer deleteSections(Integer id);
	
	List<Section> list();
	
	List<Section> findByTeacher(String id) throws Exception;

}