package com.EntranceX.mm.EntranceX.controllers;

import com.EntranceX.mm.EntranceX.dao.AdminDao;
import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dao.UserDao;
import com.EntranceX.mm.EntranceX.models.Admin;
import com.EntranceX.mm.EntranceX.models.Organizer;
import com.EntranceX.mm.EntranceX.models.User;

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
import java.util.Date;

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


    @GetMapping("/")
    public String main() {
        return "main/home";
    }

    @GetMapping("/user-page")
    public String user_home() {
        return "main/user-page";
    }

    @GetMapping("/org-page")
    public String org_home() {
        return "main/org-page";
    }

    @GetMapping("/about")
    public String about() {
        return "main/about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "main/contact";
    }

    @GetMapping("/help")
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


    @GetMapping("/ticket-voucher")
    public String ticketVoucher() {


        return "main/ticketVoucher";
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

            // User login successful
            return "redirect:/user-page";
        } else if (organizer!=null && passwordEncoder.matches(loginPassword, organizer.getPassword())) {
            // Organizer login successful
            return "redirect:/org-page";
        } else if (admin!=null && passwordEncoder.matches(loginPassword, admin.getPassword())) {
            // Admin login successful
            return "redirect:/admin";
        } else {
            redirectAttributes.addAttribute("error", true);
            // Login failed
            return "redirect:/login";
        }
    }





//    @PostMapping(value = "user/signup")
//    public String userRegister(@RequestParam("userName") String userName, @RequestParam("dateOfBirth") LocalDate dateOfBirth,
//                               @RequestParam("gender") String gender, @RequestParam("phone") String phone,
//                               @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("name")String name) {
//
//        userService.createUser(name, userName, email ,gender, phone, password, dateOfBirth);
//       ;
//        return "redirect:/login";
//    }


    @RequestMapping("/admin")
    public String admin(){
        return "admin/admin";
    }

}

