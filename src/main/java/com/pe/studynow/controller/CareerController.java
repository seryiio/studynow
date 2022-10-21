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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.studynow.business.crud.CareerService;
import com.pe.studynow.model.entity.Career;
import com.pe.studynow.model.repository.CareerRepository;

@Controller
@RequestMapping("/careers")
@SessionAttributes("{career}")
public class CareerController {
	@Autowired
	private CareerRepository careerRepository;
	@Autowired
	private CareerService careerService;
	
	@GetMapping
	public String newCareer(Model model) {
		Career career = new Career();
		model.addAttribute("career", career);
		try {
			List<Career> careers = careerService.getAll();
			model.addAttribute("careers", careers);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "careers/careers";
	}

	@PostMapping("save")
	public String saveCareer(@Valid Career career, BindingResult result, Model model, SessionStatus status, RedirectAttributes redirectAttrs)
			throws Exception {
		List<Career> careers = careerService.getAll();
		model.addAttribute("careers", careers);
		if (result.hasErrors()) {
			return "careers/careers";
		} else {
			int rpta = careerService.insert(career);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe esta carrera");
				return "careers/careers";
			} else {
				redirectAttrs.addFlashAttribute("mensaje", "Se guardo correctamente").addFlashAttribute("clase",
						"sucess");
			}
		}
		model.addAttribute("careers", careerService.list());

		return "redirect:/careers";
	}

	@GetMapping("/findOne")
	@ResponseBody
	public Optional<Career> findOne(Integer id) {
		return careerRepository.findById(id);
	}
	
	@GetMapping("{id}/edit")
	public String editCareer(Model model, @PathVariable("id") Integer id) {
		try {
			if (careerService.existById(id)) {
				Optional<Career> optional = careerService.findById(id);
				model.addAttribute("careers", optional.get());
				List<Career> courses = careerService.getAll();
				model.addAttribute("careers", courses);
			} else {
				return "redirect:/careers";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "careers/edit-career";
	}


	@PostMapping("{id}/update")
	public String updateCareer(Model model, @ModelAttribute("career") Career career,
			@PathVariable("id") Integer id, @RequestParam("newName") String name) {
		try {
			if (careerService.existById(id)) {
				career.setName(name);
				careerService.update(career);
			} else {
				return "redirect:/careers";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/careers";
	}
	
	@GetMapping("{id}/delete")
	public String deleteCareer(Model model, @PathVariable("id") Integer id) {
		try {
			if (careerService.existById(id)) {
				careerService.deleteById(id);
			} else {
				return "redirect:/careers";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/careers";
	}

}
