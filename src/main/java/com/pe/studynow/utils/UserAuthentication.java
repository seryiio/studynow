package com.pe.studynow.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.pe.studynow.business.crud.AdministratorService;
import com.pe.studynow.business.crud.StudentService;
import com.pe.studynow.business.crud.TeacherService;
import com.pe.studynow.model.entity.Administrator;
import com.pe.studynow.model.entity.Segment;
import com.pe.studynow.model.entity.Student;
import com.pe.studynow.model.entity.Teacher;
import com.pe.studynow.security.MyUserDetails;

@Service
public class UserAuthentication {
	
	// Cambiar los service de acuerdo al segmento que tengan
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private AdministratorService administratorService;
	
	public boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication instanceof AnonymousAuthenticationToken) { // Si no hay nadie autenticado
			return false;			
		} else {
			return true;
		}
	}
	
	public void getSegment(Model model) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(!(authentication instanceof AnonymousAuthenticationToken)) {	// Si Hay alguien autenticado
				
				MyUserDetails myUserDetails = (MyUserDetails)authentication.getPrincipal();
				model.addAttribute("segment", myUserDetails.getSegment());
				
				// Para el segmento 1: Cambiar STUDENT por su segmento
				if (myUserDetails.getSegment().equals(Segment.STUDENT)) {
					if (studentService.existById(myUserDetails.getIdSegment())) {
						Optional<Student> optional = studentService.findById(myUserDetails.getIdSegment());
						model.addAttribute("student", optional.get());
					}					
				} 
				// Para el segmento 2: Cambiar TEACHER por su segmento
				else if (myUserDetails.getSegment().equals(Segment.TEACHER)) {
					if (teacherService.existById(myUserDetails.getIdSegment())) {
						Optional<Teacher> optional = teacherService.findById(myUserDetails.getIdSegment());
						model.addAttribute("teacher", optional.get());
					}
				}
				
				else if (myUserDetails.getSegment().equals(Segment.ADMINISTRATOR)) {
					if (administratorService.existById(myUserDetails.getIdSegment())) {
						Optional<Administrator> optional = administratorService.findById(myUserDetails.getIdSegment());
						model.addAttribute("administrator", optional.get());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Integer getIdSegment() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken)) {	// Si Hay alguien autenticado
			
			MyUserDetails myUserDetails = (MyUserDetails)authentication.getPrincipal();
			return myUserDetails.getIdSegment();
		}
		return null;
	}
	
}
