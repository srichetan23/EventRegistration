package com.bmt.MyStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bmt.MyStore.models.Admin;
import com.bmt.MyStore.models.EventRegistration;
import com.bmt.MyStore.repositories.AdminRepository;
import com.bmt.MyStore.repositories.EventRegistrationRepository;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    @GetMapping("/adminLogin")
    public String showAdminLoginPage() {
        return "admin";
    }

    @PostMapping("/adminLogin")
    public String loginAdmin(@RequestParam String adminName,
                             @RequestParam String password,
                             Model model) {
        Admin admin = adminRepository.findByAdminNameAndPassword(adminName, password);
        if (admin != null) {
            List<EventRegistration> registrations = eventRegistrationRepository.findAllByOrderByUserEmailAsc();
            model.addAttribute("registrations", registrations);
            model.addAttribute("adminName", admin.getAdminName());
            return "adminDashBoard";
        } else {
            model.addAttribute("errorMsg", "Invalid credentials");
            return "admin";
        }
    }
}
