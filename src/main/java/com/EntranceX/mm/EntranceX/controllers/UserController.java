package com.EntranceX.mm.EntranceX.controllers;


import com.EntranceX.mm.EntranceX.dao.UserDao;
import com.EntranceX.mm.EntranceX.dto.EventDto;
import com.EntranceX.mm.EntranceX.dto.UserDto;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.User;
import com.EntranceX.mm.EntranceX.services.UserService;
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
    public String user_profile(){
        return "user/user-profile";
    }

    @GetMapping("/user-profile-update")
    public String user_update(){
        return "user/user-update";
    }

    @GetMapping("/user-this-month")
    public String user_thismonth(){ return "user/this-month"; }
    @GetMapping("/user-promotion")
    public String user_promotion(){
        return "user/promotion";
    }
    @GetMapping("/user-trending")
    public String user_trending(){
        return "user/trending";
    }
    @GetMapping("/user-upcoming")
    public String user_upcoming(){
        return "user/upcoming";
    }
    @GetMapping("/user-history")
    public String user_history(){
        return "user/history";
    }
    @GetMapping("/user-watch-later")
    public String user_watch_later(){
        return "user/watch-later";
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
    public String userRegisterPost(@ModelAttribute UserDto userDto, Model model) {
        userService.createUser(userDto);
        model.addAttribute("message", "Event created successfully!");

        return "redirect:/login";
    }

}
