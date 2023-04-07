package com.EntranceX.mm.EntranceX.controllers;

import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dto.OrganizerDto;
import com.EntranceX.mm.EntranceX.models.Organizer;
import com.EntranceX.mm.EntranceX.services.OrganizerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrgController {
    @Autowired
    OrganizerDao organizerDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    OrganizerService organizerService;

    @GetMapping("/org-page")
    public String org_home(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            return "main/org-page";
    }else {
            return "redirect:/login";
        }}

    @GetMapping("/org-profile")
    public String org_profile(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
        return "org/org-profile";
        } else{
            return "redirect:/login";
        }
    }



    @GetMapping("/org-profile-update")
    public String org_update(){return "org/org-update";}

    @GetMapping("/org-ongoing")
    public String org_ongoing(){
        return "org/ongoing";
    }
    @GetMapping("/org-future")
    public String org_future(){
        return "org/future";
    }
    @GetMapping("/org-sale-record")
    public String org_Sale_Record(){
        return "org/sale-record";
    }

    @GetMapping(value = "/org-signup")
    public String organizerRegister() {
        return "login-signup/OrganizerSignUp";
    }

    @PostMapping(value = "/org-signup")
    public String organizerRegisterPost(@ModelAttribute OrganizerDto organizerDto, Model model) {
        organizerService.createOrganizer(organizerDto);
        model.addAttribute("message", "Event created successfully!");
        return "redirect:/login";
    }
}
