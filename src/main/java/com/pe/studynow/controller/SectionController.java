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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.studynow.business.crud.CourseService;
import com.pe.studynow.business.crud.SectionService;
import com.pe.studynow.business.crud.TeacherService;
import com.pe.studynow.model.entity.Course;
import com.pe.studynow.model.entity.Section;
import com.pe.studynow.model.entity.Teacher;
import com.pe.studynow.utils.UserAuthentication;

@Controller
@RequestMapping("/sections")
@SessionAttributes("{section}")
public class SectionController {
	@Autowired
	private SectionService sectionService;

	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private UserAuthentication userAuthentication;
	

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
	public String newSection(Model model) {
		Section section = new Section();
		model.addAttribute("section", section);
		try {
			List<Section> sections = sectionService.getAll();
			model.addAttribute("sections", sections);
			List<Course> courses  = courseService.getAll();
			model.addAttribute("courses", courses);
			List<Teacher> teachers  = teacherService.getAll();
			model.addAttribute("teachers", teachers);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sections/sections";
	}

	@PostMapping("save")
	public String saveSection(@Valid Section section, BindingResult result, Model model, SessionStatus status, RedirectAttributes redirectAttrs)
			throws Exception {
		List<Section> sections = sectionService.getAll();
		model.addAttribute("sections", sections);
		if (result.hasErrors()) {
			return "sections/sections";
		} else {
			int rpta = sectionService.insert(section);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "sections/sections";
			} else {
				redirectAttrs.addFlashAttribute("mensaje", "Se guardo correctamente").addFlashAttribute("clase",
						"sucess");
			}
		}
		model.addAttribute("sections", courseService.list());

		return "redirect:/sections";
	}

	@GetMapping("{id}/edit")
	public String editSection(Model model, @PathVariable("id") Integer id) {
		try {
			if (courseService.existById(id)) {
				Optional<Section> optional = sectionService.findById(id);
				model.addAttribute("section", optional.get());
				List<Section> sections = sectionService.getAll();
				model.addAttribute("sections", sections);
				List<Course> courses = courseService.getAll();
				model.addAttribute("courses", courses);
				List<Teacher> teachers = teacherService.getAll();
				model.addAttribute("teachers", teachers);
			} else {
				return "redirect:/sections";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sections/edit-section";
	}

	@PostMapping("{id}/update")
	public String updateSection(Model model, @ModelAttribute("section") Section section, @PathVariable("id") Integer id) {
		try {
			if (sectionService.existById(id)) {
				sectionService.update(section);
			} else {
				return "redirect:/section";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/sections";
	}

	@GetMapping("{id}/delete")
	public String deleteSection(Model model, @PathVariable("id") Integer id) {
		try {
			if (sectionService.existById(id)) {
				sectionService.deleteById(id);
			} else {
				return "redirect:/sections";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/sections";
	}
	
	@GetMapping("{id}/deleteSections")
	public String deleteSections(Model model, @PathVariable("id") Integer id) {
		try {
			if (sectionService.existById(id)) {
				sectionService.deleteSections(id);
			} else {
				return "redirect:/sections";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/sections";
	}

	@GetMapping("mySections")
	public String getCoursesTeacher(Model model) {
		if (userAuthentication.isAuthenticated()) { // Enviar los datos del Segmento al html
			String id = userAuthentication.getIdSegment();
			try {
				List<Section> sections = sectionService.findByTeacher(id);
				model.addAttribute("sections", sections);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "sections/sections-teacher";
		}
		return "redirect:/sections/mySections"; // Fala corregir, genera un bucle infinito
	}
}
