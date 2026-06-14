package com.bmt.MyStore.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bmt.MyStore.models.Event;
import com.bmt.MyStore.models.EventRegistration;
import com.bmt.MyStore.models.User;
import com.bmt.MyStore.repositories.EventRegistrationRepository;
import com.bmt.MyStore.repositories.EventRepository;
import com.bmt.MyStore.repositories.UserRepository;

@Controller
public class EventController {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private EventRegistrationRepository registrationRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/eventRegPage")
	public String eventRegPage(Principal principal, Model model) {
		String email = currentUserEmail(principal);

		List<Event> events = eventRepository.findAllByOrderByIdAsc();
		Set<Integer> registered = registrationRepository.findByUserEmail(email).stream()
				.map(EventRegistration::getEventNumber)
				.collect(Collectors.toSet());

		model.addAttribute("events", events);
		model.addAttribute("registeredEventNumbers", registered);
		model.addAttribute("userName", principal != null ? principal.getName() : "");
		return "eventRegPage";
	}

	@PostMapping("/registerEvent")
	public String registerEvent(@RequestParam int eventNumber, Principal principal) {
		String email = currentUserEmail(principal);

		if (email != null && !registrationRepository.existsByUserEmailAndEventNumber(email, eventNumber)) {
			Event event = eventRepository.findById(eventNumber).orElse(null);
			if (event != null) {
				EventRegistration reg = new EventRegistration();
				reg.setUserEmail(email);
				reg.setEventNumber(eventNumber);
				reg.setEventName(event.getName());
				registrationRepository.save(reg);
			}
		}
		return "redirect:/eventRegPage";
	}

	/** The login username is the user's name; resolve it to their email for registrations. */
	private String currentUserEmail(Principal principal) {
		if (principal == null) {
			return null;
		}
		User user = userRepository.findByName(principal.getName());
		return user != null ? user.getEmail() : null;
	}
}
