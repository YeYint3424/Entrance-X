package com.EntranceX.mm.EntranceX.controllers;


import com.EntranceX.mm.EntranceX.dao.UserDao;
import com.EntranceX.mm.EntranceX.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;


@Controller
public class UserController {
    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

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
    public String userRegister(@RequestParam("name")String name, @RequestParam("userName") String userName, @RequestParam("dateOfBirth") LocalDate dateOfBirth,
                               @RequestParam("gender") String gender, @RequestParam("phone") String phone,
                               @RequestParam("email") String email, @RequestParam("password") String password) {

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(name , userName, email, gender,  phone,encodedPassword, dateOfBirth);

        userDao.save(user);
        return "redirect:/login";
    }

}
