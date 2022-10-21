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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.studynow.business.crud.CareerService;
import com.pe.studynow.business.crud.CourseService;
import com.pe.studynow.model.entity.Career;
import com.pe.studynow.model.entity.Course;

@Controller
@RequestMapping("/courses")
@SessionAttributes("{course}")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@Autowired
	private CareerService careerService;

	/*
	 * @GetMapping public String listCourses(Model model) {
	 * 
	 * try { List<Course> courses = courseService.getAll();
	 * model.addAttribute("courses", courses); } catch (Exception e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * return "courses/list-courses"; }
	 */
	@GetMapping
	public String newCourse(Model model) {
		Course course = new Course();
		model.addAttribute("course", course);
		try {
			List<Course> courses = courseService.getAll();
			model.addAttribute("courses", courses);
			List<Career> careers = careerService.getAll();
			model.addAttribute("careers", careers);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "courses/courses";
	}

	@PostMapping("save")
	public String saveCourse(@Valid Course course, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes redirectAttrs) throws Exception {
		List<Course> courses = courseService.getAll();
		model.addAttribute("courses", courses);
		List<Career> careers = careerService.getAll();
		model.addAttribute("careers", careers);
		if (result.hasErrors()) {
			return "courses/courses";
		} else {
			int rpta = courseService.insert(course);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe este curso");
				return "courses/courses";
			} else {
				redirectAttrs.addFlashAttribute("mensaje", "Se guardo correctamente").addFlashAttribute("clase",
						"sucess");
			}
		}
		model.addAttribute("courses", courseService.list());

		return "redirect:/courses";
	}

	@GetMapping("{id}/edit")
	public String editCourse(Model model, @PathVariable("id") Integer id) {
		try {
			if (courseService.existById(id)) {
				Optional<Course> optional = courseService.findById(id);
				model.addAttribute("course", optional.get());
				List<Course> courses = courseService.getAll();
				model.addAttribute("courses", courses);
				List<Career> careers = careerService.getAll();
				model.addAttribute("careers", careers);
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
	public String updateCourse(Model model, @ModelAttribute("course") Course course, @PathVariable("id") Integer id,
			@RequestParam("newName") String name, @RequestParam("newCycle") Integer cycle,
			@RequestParam("newCreditAmount") Integer creditAmount, @RequestParam("newCareer") Career careerName) {
		try {
			if (careerService.existById(id)) {
				course.setName(name);
				course.setCycle(cycle);
				course.setNumberCredits(creditAmount);
				course.setCareer(careerName);
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
			if (courseService.existById(id)) {
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

	@GetMapping("{id}/deleteCourses")
	public String deleteCourses(Model model, @PathVariable("id") Integer id) {
		try {
			if (courseService.existById(id)) {
				courseService.deleteCourses(id);
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
