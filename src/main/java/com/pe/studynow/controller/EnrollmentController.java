package com.pe.studynow.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.studynow.business.crud.CourseService;
import com.pe.studynow.business.crud.EnrollmentService;
import com.pe.studynow.business.crud.SectionService;
import com.pe.studynow.business.crud.StudentService;
import com.pe.studynow.model.entity.Course;
import com.pe.studynow.model.entity.Enrollment;
import com.pe.studynow.model.entity.Section;
import com.pe.studynow.model.entity.Student;
import com.pe.studynow.utils.UserAuthentication;

@Controller
@RequestMapping("/enrollments")
@SessionAttributes("{enrollment}")
public class EnrollmentController {
	@Autowired
	private StudentService studentService;

	@Autowired
	private SectionService sectionService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private EnrollmentService enrollmentService;

	@Autowired
	private UserAuthentication userAuthentication;

	@GetMapping
	public String listEnrollment(Model model) {

		if (userAuthentication.isAuthenticated()) { // Enviar los datos del Segmento al html
			userAuthentication.getSegment(model);
		}
		try {
			Enrollment enrollment = new Enrollment();
			model.addAttribute("enrollment", enrollment);
			List<Enrollment> enrollments = enrollmentService.getAll();
			model.addAttribute("enrollments", enrollments);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			List<Course> courses = courseService.getAll();
			model.addAttribute("courses", courses);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			List<Section> sections = sectionService.getAll();
			model.addAttribute("sections", sections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "enrollments/enrollments";
	}

	@GetMapping("new")
	public String newEnrollment(Model model) {
		Enrollment enrollment = new Enrollment();
		model.addAttribute("enrollment", enrollment);
		try {
			List<Section> sections = sectionService.getAll();
			model.addAttribute("sections", sections);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "enrollments/new-enrollment";
	}

	@PostMapping("savenew")
	public String saveEnrollment(Model model, @ModelAttribute("enrollment") Enrollment enrollment, SessionStatus status,
			RedirectAttributes redirectAttrs) {
		if (userAuthentication.isAuthenticated()) { // Enviar los datos del Segmento al html
			String id = userAuthentication.getIdSegment();
			try {
				if (studentService.existById(id)) {
					Optional<Student> optional = studentService.findById(id);
					enrollment.setNumberCycle("2022-02");
					enrollment.setStudent(optional.get());
					int vacancies = enrollment.getSection().getVacancies();
					int newvacancies = vacancies - 1;
					enrollment.getSection().setVacancies(newvacancies);
					int creditCourse = enrollment.getSection().getCourse().getNumberCredits();
					int creditAmount = enrollment.getStudent().getCreditAmount();
					int newcreditAmount = creditAmount - creditCourse;
					enrollment.getSection().setVacancies(newvacancies);
					enrollment.getStudent().setCreditAmount(newcreditAmount);

					int rpta2 = enrollmentService.verifyVacancies(enrollment);
					int rpta3 = enrollmentService.veriflyCredits(enrollment);
					int rpta = enrollmentService.insert(enrollment);
					if (rpta >= 1) {
						redirectAttrs.addFlashAttribute("mensaje", "Elimine una seccion para seleccionar otra")
								.addFlashAttribute("clase", "danger");
					} else if (rpta <= 1 && rpta2 > 0) {
						redirectAttrs.addFlashAttribute("mensaje", "Se guardo correctamente").addFlashAttribute("clase",
								"sucess");
					}

					if (rpta <= 1 && rpta2 <= 0) {
						redirectAttrs.addFlashAttribute("mensaje", "No hay vacantes, seleccione otra seccion")
								.addFlashAttribute("clase", "warning");
					}
					if (rpta <= 1 && rpta2 > 0 && rpta3 < 0) {
						redirectAttrs.addFlashAttribute("mensaje", "Creditos insuficientes").addFlashAttribute("clase",
								"warning");
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/enrollments";
	}

	@GetMapping("{id}/delete")
	public String deleteEnrollment(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttrs) {
		try {

			if (enrollmentService.existById(id)) {
				enrollmentService.Restaurar(id);
				enrollmentService.deleteById(id);
				redirectAttrs.addFlashAttribute("mensaje", "Se elimino correctamente").addFlashAttribute("clase",
						"success");
			} else {
				return "redirect:/enrollments";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/enrollments";
	}
	
	@RequestMapping("/report")
	public String listEnrollmentforCycle(Map<String, Object> model) {

		try {
			model.put("reporte1", enrollmentService.Reporte1());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "enrollments/report-enrollment";
	}
}
