package com.EntranceX.mm.EntranceX.controllers;


import com.EntranceX.mm.EntranceX.dao.UserDao;
import com.EntranceX.mm.EntranceX.dto.EventDto;
import com.EntranceX.mm.EntranceX.dto.UserDto;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.User;
import com.EntranceX.mm.EntranceX.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
public class UserController {
    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @GetMapping("/user-profile")
    public String user_profile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
        return "user/user-profile";
    } else {
        return "redirect:/login";     }
        }

    @GetMapping("/user-profile-update")
    public String user_update(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
        return "user/user-update";
    }else {
            return "redirect:/login";
        }
        }

    @GetMapping("/user-this-month")
    public String user_thismonth(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            return "user/this-month";
        }else {
            return "redirect:/login";
        }
        }

    @GetMapping("/user-promotion")
    public String user_promotion(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            return "user/promotion";
    }else {
            return "redirect:/login";
        }
        }

    @GetMapping("/user-trending")
    public String user_trending(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
        return "user/trending";
    }else {
            return "redirect:/login";
        }
        }

    @GetMapping("/user-upcoming")
    public String user_upcoming(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
        return "user/upcoming";
    }else {
            return "redirect:/login";
        }
        }

    @GetMapping("/user-history")
    public String user_history(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
        return "user/history";
    }else {
            return "redirect:/login";
        }
        }
    @GetMapping("/user-watch-later")
    public String user_watch_later(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
        return "user/watch-later";
    }else {
            return "redirect:/login";
        }
        }

    @GetMapping("/user-about")
    public String userAbout(){
        return "user/about";
    }
    @GetMapping("/user-help")
    public String userHelp(){
        return "user/help";
    }
    @GetMapping("/user-term")
    public String userTerm(){
        return "user/termCondition";
    }
    @GetMapping("/user-privacyPolicy")
    public String userPrivacyPolicy(){
        return "user/privacyPolicy";
    }
    @GetMapping("/user-contact")
    public String userContact(){
        return "user/contact";
    }

    @GetMapping(value = "/user-signup")
    public String userRegister() {
        return "login-signup/UserSignUp";
    }

    @PostMapping(value = "/user-signup")
    public String userRegisterPost(@ModelAttribute UserDto userDto) {
        userService.createUser(userDto);


        return "redirect:/login";
    }

}
