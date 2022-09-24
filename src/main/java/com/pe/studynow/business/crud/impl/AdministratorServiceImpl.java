package com.pe.studynow.business.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.pe.studynow.business.crud.AdministratorService;
import com.pe.studynow.model.entity.Administrator;
import com.pe.studynow.model.repository.AdministratorRepository;

@Service
public class AdministratorServiceImpl implements AdministratorService{

	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Override
	public JpaRepository<Administrator, Integer> getJpaRepository() {
		return this.administratorRepository;
	}
}
