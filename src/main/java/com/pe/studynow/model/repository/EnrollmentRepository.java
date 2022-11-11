package com.pe.studynow.model.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.studynow.model.entity.Enrollment;
import com.pe.studynow.model.entity.Section;
import com.pe.studynow.model.entity.Student;


@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{

	List<Enrollment> findByStudent(Student student) throws Exception;
	List<Enrollment> findBySection(Section section) throws Exception;
	
	@Query(	value="select count(s.course_id) from enrollments e inner join sections s on e.section_id=s.id where s.course_id=:curso and e.student_id=:alumno", nativeQuery = true )
	public int BuscarCurso(@Param("curso") Integer curso,@Param("alumno") String alumno);

	@Query(value="select s.vacancies from sections s where s.id=:seccion", nativeQuery = true )
	public int BuscarVacantes(@Param("seccion") Integer seccion);
	
	@Query(value="select s.credit_amount from students s where s.student_id=:estudiante", nativeQuery = true )
	public int BuscarCreditos(@Param("estudiante") String estudiante);
	
	@Transactional
    @Modifying
    @Query(value="update students set credit_amount = students.credit_amount + cou.number_credits from students stu inner join enrollments en ON stu.student_id=en.student_id inner join sections sec ON en.section_id = sec.id inner join courses cou ON sec.course_id=cou.id where en.enrollment_id=:matricula AND students.student_id=en.student_id", nativeQuery = true )
    public void RestaurarCreditos(@Param("matricula") Integer matricula);
	
	@Transactional
    @Modifying
    @Query(value="update sections set vacancies = sec.vacancies+1 from sections sec inner join enrollments en ON sec.id=en.section_id where en.enrollment_id=:matricula AND sections.id =en.section_id AND sections.vacancies<10 AND sections.vacancies>0 ", nativeQuery = true )
    public void RestaurarVacantes(@Param("matricula") Integer matricula);
	
	@Query(value= "SELECT students.student_id,students.first_name, students.last_name,email_university,email_personal,phone_number,cycle, career_id  FROM Students INNER JOIN Enrollments ON Students.student_id=Enrollments.student_id GROUP BY Students.student_id", nativeQuery = true)
	public List<String[]> Reporte1();
}
