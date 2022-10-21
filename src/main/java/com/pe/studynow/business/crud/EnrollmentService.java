package com.pe.studynow.business.crud;

import java.util.List;

import com.pe.studynow.model.entity.Enrollment;

public interface EnrollmentService extends CrudService<Enrollment, Integer>{

	List<Enrollment> findByStudent(String id) throws Exception;

	public Integer insert(Enrollment enrollment);
	
	public Integer verifyVacancies(Enrollment enrollment);
	
	public Integer veriflyCredits(Enrollment enrollment);
	
	public void Restaurar(Integer IDenrollment);
}
