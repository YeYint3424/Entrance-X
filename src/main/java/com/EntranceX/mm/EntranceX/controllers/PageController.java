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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class PageController {


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
    public String LoginPagePost(@RequestParam ("userName") String userName, @RequestParam ("password")String password, HttpServletRequest request) {

        User user = userDao.findByUserNameAndPassword(userName, password);
        Organizer organizer = organizerDao.findByUserNameAndPassword(userName, password);
        Admin admin = adminDao.findByUserNameAndPassword(userName, password);

        if (user != null) {
            // User login successful
            return "redirect:/user-page";
        } else if (organizer != null) {
            // Organizer login successful
            return "redirect:/org-page";
        } else if (admin != null) {
            // Admin login successful
            return "redirect:/admin";
        } else {
            // Login failed
            return "redirect:/login";
        }
    }


    @GetMapping(value = "user/signup")
    public String userRegister() {
        return "login-signup/UserSignUp";
    }

    @PostMapping(value = "user/signup")
    public String userRegister(@RequestParam("name")String name, @RequestParam("userName") String userName, @RequestParam("dateOfBirth") LocalDate dateOfBirth,
                               @RequestParam("gender") String gender, @RequestParam("phone") String phone,
                               @RequestParam("email") String email, @RequestParam("password") String password) {
//        int ph=Integer.valueOf(phone);
        User user = new User(name , userName, email, gender,  phone,password, dateOfBirth);

        userDao.save(user);
        return "redirect:/login";
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

