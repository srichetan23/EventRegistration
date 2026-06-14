package com.bmt.MyStore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bmt.MyStore.models.Admin;
import com.bmt.MyStore.models.Event;
import com.bmt.MyStore.models.EventRegistration;
import com.bmt.MyStore.repositories.AdminRepository;
import com.bmt.MyStore.repositories.EventRegistrationRepository;
import com.bmt.MyStore.repositories.EventRepository;
import com.bmt.MyStore.repositories.UserRepository;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/adminLogin")
    public String showAdminLoginPage() {
        return "admin";
    }

    @PostMapping("/adminLogin")
    public String loginAdmin(@RequestParam String adminName,
                             @RequestParam String password,
                             Model model) {
        Admin admin = adminRepository.findByAdminNameAndPassword(adminName, password);
        if (admin == null) {
            model.addAttribute("errorMsg", "Invalid credentials");
            return "admin";
        }

        List<EventRegistration> registrations = eventRegistrationRepository.findAllByOrderByUserEmailAsc();
        List<Event> events = eventRepository.findAllByOrderByIdAsc();

        // Chart data: registrations per event.
        List<String> chartLabels = new ArrayList<>();
        List<Long> chartData = new ArrayList<>();
        for (Event e : events) {
            chartLabels.add(e.getName());
            chartData.add(eventRegistrationRepository.countByEventNumber(e.getId()));
        }

        model.addAttribute("adminName", admin.getAdminName());
        model.addAttribute("registrations", registrations);
        model.addAttribute("totalUsers", userRepository.count());
        model.addAttribute("totalRegistrations", registrations.size());
        model.addAttribute("totalEvents", events.size());
        model.addAttribute("chartLabels", chartLabels);
        model.addAttribute("chartData", chartData);
        return "adminDashBoard";
    }
}
