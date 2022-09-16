package com.pe.studynow.controller;

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

import com.pe.studynow.business.crud.CourseService;
import com.pe.studynow.model.entity.Course;

@Controller
@RequestMapping("/courses")
@SessionAttributes("{course}")
public class CourseController {
	@Autowired
	private CourseService courseService;
/*
	@GetMapping	
	public String listCourses(Model model) {
		
		try {
			List<Course> courses = courseService.getAll();
			model.addAttribute("courses", courses);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "courses/list-courses";
	}
	*/
	@GetMapping
	public String newCourse(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		try {
			List<Course> courses = courseService.getAll();
			model.addAttribute("courses", courses);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "courses/list-courses";
	}
	
	@PostMapping("save")
	public String saveCourse(@Valid Course course, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "redirect:/courses";
		} else {
			int rpta = courseService.insert(course);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "redirect:/courses";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listCourses", courseService.list());

		return "redirect:/courses";
	}
	
	@GetMapping("{id}/edit")
	public String editCourse(Model model, @PathVariable("id") Integer id) {
			try {
				if(courseService.existById(id)) {
					Optional<Course> optional = courseService.findById(id);
					model.addAttribute("course", optional.get());
					List<Course> courses = courseService.getAll();
					model.addAttribute("courses", courses);
				} else {
					return "redirect:/courses";
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return "courses/edit-course";
	}
	
	@PostMapping("{id}/update")
	public String updateCourse(Model model, @ModelAttribute("course") Course course, @PathVariable("id") Integer id) {
		try {
			if(courseService.existById(id)) {
				courseService.update(course);
			} else {
				return "redirect:/courses";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/courses";
	}
	
	@GetMapping("{id}/delete")
	public String deleteCourse(Model model, @PathVariable("id") Integer id) {
		try {
			if(courseService.existById(id)) {
				courseService.deleteById(id);
			} else {
				return "redirect:/courses";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/courses";
	}
}
