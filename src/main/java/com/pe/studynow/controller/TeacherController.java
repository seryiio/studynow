package com.pe.studynow.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.studynow.business.crud.CareerService;
import com.pe.studynow.business.crud.TeacherService;
import com.pe.studynow.model.entity.Career;
import com.pe.studynow.model.entity.Segment;
import com.pe.studynow.model.entity.Teacher;
import com.pe.studynow.model.entity.User;

@Controller
@RequestMapping("/teachers")
@SessionAttributes("{teacher}")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private CareerService careerService;

	@GetMapping
	public String listTeacher(Model model) {

		try {
			List<Teacher> teachers = teacherService.getAll();

			model.addAttribute("teachers", teachers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "teachers/teachers";
	}

	@GetMapping("new")
	public String newTeacher(Model model) {
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		User user = new User();
		user.setEnable(true);
		model.addAttribute("user", user);
		model.addAttribute("segments", getSegments());
		try {
			List<Career> careers = careerService.getAll();
			model.addAttribute("careers", careers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "teachers/new-teacher";
	}

	@PostMapping("save")
	public String saveTeacher(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes redirectAttrs) {
		try {
			if (result.hasErrors()) {
				List<Career> careers = careerService.getAll();
				model.addAttribute("careers", careers);
				return "teachers/new-teacher";
			} else {
				int rpta = teacherService.insert(teacher);
				if (rpta > 0) {
					model.addAttribute("mensaje", "Ya existe");
					return "teachers/teachers";
				} 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:/users/new";
	}

	@GetMapping("{id}/edit")
	public String editTeacher(Model model, @PathVariable("id") String id) {
		try {
			if (teacherService.existById(id)) {
				Optional<Teacher> optional = teacherService.findById(id);
				model.addAttribute("teacher", optional.get());
				List<Teacher> teachers = teacherService.getAll();
				model.addAttribute("teachers", teachers);
				List<Career> careers = careerService.getAll();
				model.addAttribute("careers", careers);
			} else {
				return "redirect:/teachers";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "teachers/edit-teacher";
	}

	@PostMapping("{id}/update")
	public String updateTeacher(Model model, @ModelAttribute("teacher") Teacher teacher,
			@PathVariable("id") String id) {
		try {
			if (teacherService.existById(id)) {
				teacherService.update(teacher);
			} else {
				return "redirect:/teachers";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/teachers";
	}

	@GetMapping("{id}/delete")
	public String deleteTeacher(Model model, @PathVariable("id") String id) {
		try {
			if (teacherService.existById(id)) {
				teacherService.deleteById(id);
			} else {
				return "redirect:/teachers";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/teachers";
	}

	private List<Segment> getSegments() {
		List<Segment> segments = new ArrayList<>();
		segments.add(Segment.STUDENT);
		segments.add(Segment.TEACHER);
		return segments;
	}
	
	/*
	@GetMapping("enroll-course")
	public String newCourseforTeacher(Model model) {
		TeacherxCourse teacherxcourse = new TeacherxCourse();
		model.addAttribute("teacherxcourse", teacherxcourse);
		try {
			if (userAuthentication.isAuthenticated()) { // Enviar los datos del Segmento al html
				userAuthentication.getSegment(model);
				Course course = new Course();
				model.addAttribute("course", course);
				List<Course> courses = courseService.getAll();
				model.addAttribute("courses", courses);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.addSuppressed(e);
		}
		return "courses/new-course-teacher";
	}

	@PostMapping("save-new-course")
	public String saveNewCourseforTeacher(Model model, @ModelAttribute("teacherxcourse") TeacherxCourse teacherxcourse ) {
		if (userAuthentication.isAuthenticated()) {	// Enviar los datos del Segmento al html
			String id = userAuthentication.getIdSegment();
			try {				
				if (teacherService.existById(id)) {
					System.out.println("encontro");
					Optional<Teacher> optional = teacherService.findById(id);
					teacherxcourse.setTeacher(optional.get());
					teacherxcourseService.create(teacherxcourse);
					System.out.println("grabo");
					return "redirect:/myCourses";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/myCourses";
	}*/

}
