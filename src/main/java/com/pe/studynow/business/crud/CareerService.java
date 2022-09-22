package com.pe.studynow.business.crud;

import java.util.List;

import com.pe.studynow.model.entity.Career;

public interface CareerService extends CrudService<Career, Integer>{

	public Integer insert(Career career);
	
	List<Career> list();
}
