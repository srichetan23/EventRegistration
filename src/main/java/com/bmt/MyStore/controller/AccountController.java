package com.bmt.MyStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bmt.MyStore.models.RegisterDto;
import com.bmt.MyStore.models.User;
import com.bmt.MyStore.repositories.UserRepository;

import jakarta.validation.Valid;

@Controller
public class AccountController {

	@Autowired
	private UserRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("registerDto", new RegisterDto());
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registerDto") RegisterDto registerDto,
			BindingResult result) {

		// Reject duplicates so we don't blow up on the unique email constraint.
		if (registerDto.getEmail() != null && repo.findByEmail(registerDto.getEmail()) != null) {
			result.rejectValue("email", null, "An account with this email already exists");
		}
		if (registerDto.getName() != null && repo.findByName(registerDto.getName()) != null) {
			result.rejectValue("name", null, "This name is already taken");
		}

		if (result.hasErrors()) {
			return "register";
		}

		User user = new User();
		user.setName(registerDto.getName());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		repo.save(user);

		return "redirect:/login?registered";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
