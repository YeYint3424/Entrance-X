package com.EntranceX.mm.EntranceX.controllers;

import com.EntranceX.mm.EntranceX.dao.AdminDao;
import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dao.UserDao;
import com.EntranceX.mm.EntranceX.dto.AdminDto;
import com.EntranceX.mm.EntranceX.models.Admin;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.Organizer;
import com.EntranceX.mm.EntranceX.models.User;

import com.EntranceX.mm.EntranceX.services.AdminService;
import com.EntranceX.mm.EntranceX.services.EventService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Autowired
    AdminDao adminDao;

    @Autowired
    OrganizerDao organizerDao;

    @Autowired
    AdminService adminService;

    @Autowired
    EventService eventService;

    @GetMapping("/")
    public String main(Model model) {
        List<Event> events=eventService.getEvents();
        for(Event event: events){
            byte[]photo= Base64.getDecoder().decode(event.getEncodedPhoto());
        }
        model.addAttribute("events", events);
        model.addAttribute("localDate", LocalDate.now());
        return "main/home";
    }

    @GetMapping("/user-page")
    public String user_home(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
        return "main/user-page";
        } else {
            return "redirect:/login";
        }
    }



    @GetMapping("/about")
    public String about() {
        return "main/about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "main/contact";
    }

    @GetMapping("/faq")
    public String help() {
        return "main/help";
    }

    @GetMapping("/privacypolicy")
    public String privacypolicy() {
        return "main/privacyPolicy";
    }

    @GetMapping("/termsandconditions")
    public String terms() {
        return "main/termCondition";
    }




    @GetMapping(value = "/login")
    public String LoginPage() {
        return "login-signup/Login";
    }


    @PostMapping(value = "/login")
    public String LoginPagePost(@RequestParam ("userName") String userName, @RequestParam ("loginPassword")String loginPassword, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        User user = userDao.findByUserName(userName);
        Organizer organizer = organizerDao.findByUserName(userName);
        Admin admin = adminDao.findByUserName(userName);

        if (user!=null && passwordEncoder.matches(loginPassword, user.getPassword())) {

            HttpSession session = request.getSession();
            session.setAttribute("LoginUser",user.getId());

            // User login successful
            return "redirect:/user-page";
        } else if (organizer!=null && passwordEncoder.matches(loginPassword, organizer.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("LoginOrganizer",organizer.getId());

            // Organizer login successful
            return "redirect:/org-page";
        } else if (admin!=null && passwordEncoder.matches(loginPassword, admin.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("LoginAdmin",admin.getId());

            // Admin login successful
            return "redirect:/admin";
        } else {
            redirectAttributes.addAttribute("error", true);
            // Login failed
            return "redirect:/login";
        }
    }
    @GetMapping("/sign-out")
    public String signOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); }
        return "redirect:/";
    }





    @GetMapping("/entrancex-control-station")
    public String adminSignUp(){
        return "login-signup/AdminSignUp";
    }
    @PostMapping("/entrancex-control-station")
    public String adminSignUpPost(AdminDto adminDto){
        adminService.createAdmin(adminDto);
        return "redirect:/login";
    }
}


