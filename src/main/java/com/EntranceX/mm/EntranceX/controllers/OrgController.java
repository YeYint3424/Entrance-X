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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            return "main/org-page";
        } else{
                return "redirect:/login";} }

     @GetMapping("/org-about")
     public String orgAbout(){
        return "org/about";
     }

    @GetMapping("/org-faq")
    public String orgFaq(){
        return "org/help";
    }

    @GetMapping("/org-contact")
    public String orgContact(){
        return "org/contact";
    }

    @GetMapping("/org-term")
    public String orgTerm(){
        return "org/termCondition";
    }

    @GetMapping("/org-privacyPolicy")
    public String orgPrivacy(){
        return "org/privacyPolicy";
    }

    @GetMapping("/org-profile")
    public String org_profile(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            int organizerId=(int)session.getAttribute("LoginOrganizer");
            Organizer organizerData=organizerService.getOrganizerById(organizerId);
            model.addAttribute("organizerData", organizerData);
        return "org/org-profile";
        } else{
            return "redirect:/login"; } }

    @GetMapping("/org-profile-update")
    public String org_update(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            int organizerId=(int)session.getAttribute("LoginOrganizer");
            Organizer organizerData=organizerService.getOrganizerById(organizerId);
            model.addAttribute("organizerEdit", organizerData);
            return "org/org-update";
        } else {
            return "redirect:/login"; } }

    @PostMapping("/org-profile-update")
    public String org_updatePost(HttpServletRequest request, Model model, OrganizerDto organizerDto,
                                 @RequestParam("oldPassword") String oldPassword, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            int organizerId=(int)session.getAttribute("LoginOrganizer");
            Organizer organizerData=organizerService.getOrganizerById(organizerId);
            model.addAttribute("organizerData", organizerData);
            if(passwordEncoder.matches(oldPassword, organizerData.getPassword()) && !passwordEncoder.matches(organizerDto.getPassword(),organizerData.getPassword())){
                organizerService.editProfile(organizerDto, organizerId);
                return "redirect:/org-profile";
            }else if(passwordEncoder.matches(organizerDto.getPassword(),organizerData.getPassword())){
                redirectAttributes.addAttribute("alreadyUse", true);
                return "redirect:/org-profile-update";
            }else{
                redirectAttributes.addAttribute("error", true);
                return "redirect:/org-profile-update";
            }
        } else {
            return "redirect:/login"; } }

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
            model.addAttribute("localDate", LocalDate.now());
            return "org/ongoing";
        }else{
            return "redirect:/login"; } }


    @GetMapping("/org-future")
    public String org_future(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
        return "org/future";
    }else{
            return "redirect:/login"; } }

    @GetMapping("/org-sale-record")
    public String org_Sale_Record(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
        return "org/sale-record";
    }else{
            return "redirect:/login"; } }

    @GetMapping(value = "/org-signup")
    public String organizerRegister() {
        return "login-signup/OrganizerSignUp";
        }

    @PostMapping(value = "/org-signup")
    public String organizerRegisterPost(@ModelAttribute OrganizerDto organizerDto, Model model) {
        organizerService.createOrganizer(organizerDto);
        return "redirect:/login";
    }

    @GetMapping("/org-search-page")
    public String org_search(HttpServletRequest request, @RequestParam("eventName") String eventName, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            List<Event> events = eventService.getEventForSearch(eventName);
            for (Event event : events) {
                byte[] photoByte = Base64.getDecoder().decode(event.getEncodedPhoto().getBytes());
            }
            model.addAttribute("searchName", eventName);
            model.addAttribute("searchEvents", events);
            return "org/search-page";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/org-admin-event-detail")
    public String org_admin_event_detail(){
        return "org/event-detail";
    }
}
