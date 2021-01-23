package com.zack.projects.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zack.projects.chatapp.exception.ResourceExistsException;
import com.zack.projects.chatapp.model.User;

@Controller
@RequestMapping("/")
public class TemplateController {
	
	@Autowired
	private UserController userController;

	// login
	@GetMapping("login")
	public String getLoginView() {
		
		return "login";
		
	}
	
	// login-error
	@GetMapping("login-error")
	public String getLoginErrorView(Model model) {
		
		model.addAttribute("loginError", true);
		return "login";
			
	}
	
	// Open registration form
	@GetMapping("register")
	public String openRegistrationform(Model model) {
		
		model.addAttribute("user", new User());
		return "register";		
		
	}
	
	@PostMapping("register")
	public String registerUser(
			@ModelAttribute("user") User user,
			final RedirectAttributes redirectAttributes) throws ResourceExistsException {
		
		redirectAttributes.addAttribute("user", this.userController.addUser(user));
		
		return "registersuccessful";
		
	}
	
	// Open chatapp home page
	@GetMapping("chatapp")
	public String getChatapp() {
		
		return "chatapp";
		
	}

}
