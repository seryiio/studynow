package com.pe.studynow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.studynow.business.crud.UserService;
import com.pe.studynow.model.entity.Segment;
import com.pe.studynow.model.entity.User;
import com.pe.studynow.utils.UserAuthentication;

@Controller
@RequestMapping("/users")
@SessionAttributes("{user}")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserAuthentication userAuthentication;
	

	@GetMapping
	public String listUsers(Model model) {

		try {
			List<User> users = userService.getAll();
			model.addAttribute("users", users);
			model.addAttribute("segments", getSegments());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "users/users";
	}
	
	@GetMapping("new")
	public String newUser(Model model) {
		User user = new User();
		user.setEnable(true);
		model.addAttribute("user", user);
		model.addAttribute("segments", getSegments());
		
		return "users/new-user";
	}
	
	@PostMapping("save")
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, SessionStatus status,
			RedirectAttributes redirectAttrs) {
		try {
			if (result.hasErrors()) {
				return "user/new-user";
			} else {
				if (!userService.existsByUsername(user.getUsername())) {
					userService.register(user);
					{
						redirectAttrs.addFlashAttribute("mensaje", "Se guardo correctamente").addFlashAttribute("clase",
								"sucess");
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return "redirect:/teachers";

	}
	
	@GetMapping("view-profile")
	public String getIndex(Model model) {	
		
		if (userAuthentication.isAuthenticated()) {	// Enviar los datos del Segmento al html
			userAuthentication.getSegment(model);
		}
		return "users/view-profile";
	}
	private List<Segment> getSegments() {
		List<Segment> segments = new ArrayList<>();
		segments.add(Segment.STUDENT);
		segments.add(Segment.TEACHER);
		return segments;
	}
}