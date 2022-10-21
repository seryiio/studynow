package com.pe.studynow.business.crud.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.studynow.business.crud.SectionService;
import com.pe.studynow.model.entity.Section;
import com.pe.studynow.model.entity.Teacher;
import com.pe.studynow.model.repository.SectionRepository;
import com.pe.studynow.model.repository.TeacherRepository;

@Service
public class SectionServiceImpl implements SectionService{
	@Autowired
	private SectionRepository sectionRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public JpaRepository<Section, Integer> getJpaRepository() {
		// TODO Auto-generated method stub
		return  this.sectionRepository;
	}

	@Override
	@Transactional
	public Integer insert(Section section) {
		int rpta = sectionRepository.buscarNombreSeccion(section.getName());
		if (rpta == 0) {
			sectionRepository.save(section);
		}
		return rpta;
	}
	@Override
	public List<Section> findByTeacher(String id) throws Exception {
		if (teacherRepository.existsById(id)) {
			Optional<Teacher> optional = teacherRepository.findById(id);
			return sectionRepository.findByTeacher(optional.get());
		} else {
			return new ArrayList<Section>();
		}		
	}

	@Override
	@Transactional
	public Integer deleteSections(Integer id) {
		int rpta = sectionRepository.eliminarMuchasSecciones(id);
		return rpta;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Section> list() {
		// TODO Auto-generated method stub
		return sectionRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
	}

}
