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
import com.pe.studynow.business.crud.StudentService;
import com.pe.studynow.model.entity.Career;
import com.pe.studynow.model.entity.Segment;
import com.pe.studynow.model.entity.Student;
import com.pe.studynow.model.entity.User;

@Controller
@RequestMapping("/students")
@SessionAttributes("{student}")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@Autowired
	private CareerService careerService;

	@GetMapping
	public String listStudent(Model model) {

		try {
			List<Student> students = studentService.getAll();

			model.addAttribute("students", students);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "students/students";
	}

	@GetMapping("new")
	public String newStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
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
		return "students/new-student";
	}

	@PostMapping("save")
	public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes redirectAttrs) {
		try {
			if (result.hasErrors()) {
				List<Career> careers = careerService.getAll();
				model.addAttribute("careers", careers);
				return "students/new-student";
			} else {
				int rpta = studentService.insert(student);
				if (rpta > 0) {
					model.addAttribute("mensaje", "Ya existe");
					return "students/new-student";
				} 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "redirect:/users/new";
	}

	@GetMapping("{id}/edit")
	public String editStudent(Model model, @PathVariable("id") String id) {
		try {
			if (studentService.existById(id)) {
				Optional<Student> optional = studentService.findById(id);
				model.addAttribute("student", optional.get());
				List<Student> students = studentService.getAll();
				model.addAttribute("students", students);
				List<Career> careers = careerService.getAll();
				model.addAttribute("careers", careers);
			} else {
				return "redirect:/students";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "students/edit-student";
	}

	@PostMapping("{id}/update")
	public String updateStudent(Model model, @ModelAttribute("student") Student student,
			@PathVariable("id") String id) {
		try {
			if (studentService.existById(id)) {
				studentService.update(student);
			} else {
				return "redirect:/students";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/students";
	}

	@GetMapping("{id}/delete")
	public String deleteStudent(Model model, @PathVariable("id") String id) {
		try {
			if (studentService.existById(id)) {
				studentService.deleteById(id);
			} else {
				return "redirect:/students";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/students";
	}

	private List<Segment> getSegments() {
		List<Segment> segments = new ArrayList<>();
		segments.add(Segment.STUDENT);
		segments.add(Segment.TEACHER);
		return segments;
	}
	
}
