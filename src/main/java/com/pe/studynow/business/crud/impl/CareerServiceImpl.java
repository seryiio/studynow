package com.pe.studynow.business.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.studynow.business.crud.CareerService;
import com.pe.studynow.model.entity.Career;
import com.pe.studynow.model.repository.CareerRepository;

@Service
public class CareerServiceImpl implements CareerService{
	@Autowired
	private CareerRepository careerRepository;
	
	@Override
	public JpaRepository<Career, Integer> getJpaRepository() {
		// TODO Auto-generated method stub
		return  this.careerRepository;
	}

	@Override
	@Transactional
	public Integer insert(Career career) {
		int rpta = careerRepository.buscarNombreCarrera(career.getName());
		if (rpta == 0) {
			careerRepository.save(career);
		}
		return rpta;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Career> list() {
		// TODO Auto-generated method stub
		return careerRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
	}
}
