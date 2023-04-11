package com.EntranceX.mm.EntranceX.controllers;

import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dto.OrganizerDto;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.Organizer;
import com.EntranceX.mm.EntranceX.services.EventService;
import com.EntranceX.mm.EntranceX.services.OrganizerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class OrgController {
    @Autowired
    OrganizerDao organizerDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    OrganizerService organizerService;

    @Autowired
    EventService eventService;

    @GetMapping("/org-page")
    public String org_home(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            List<Organizer> organizers = organizerDao.findAll();


                return "main/org-page"; }
        else{
                return "redirect:/login";
            }
        }
    @GetMapping("/org-profile")
    public String org_profile(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
        return "org/org-profile";
        } else{
            return "redirect:/login";
        }
    }



    @GetMapping("/org-profile-update")
    public String org_update(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            return "org/org-update";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping("/org-ongoing")
    public String org_ongoing(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            int organizerId=(int)session.getAttribute("LoginOrganizer");
            List<Event> events = eventService.getEventsByOrganizerId(organizerId);
            for (Event event : events) {
                byte[]photoByte=Base64.getDecoder().decode(event.getEncodedPhoto().getBytes());


            }
            model.addAttribute("events", events);

            return "org/ongoing";
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/org-future")
    public String org_future(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
        return "org/future";
    }else {
            return "redirect:/login";
        }
        }
    @GetMapping("/org-sale-record")
    public String org_Sale_Record(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
        return "org/sale-record";
    }else {
            return "redirect:/login";
        }
        }

    @GetMapping(value = "/org-signup")
    public String organizerRegister() {
        return "login-signup/OrganizerSignUp";
        }

    @PostMapping(value = "/org-signup")
    public String organizerRegisterPost(@ModelAttribute OrganizerDto organizerDto, Model model) {
        organizerService.createOrganizer(organizerDto);
        return "redirect:/login";
    }
}
