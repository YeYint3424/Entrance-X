package com.EntranceX.mm.EntranceX.controllers;


import com.EntranceX.mm.EntranceX.dao.UserDao;
import com.EntranceX.mm.EntranceX.dto.EventDto;
import com.EntranceX.mm.EntranceX.dto.UserDto;
import com.EntranceX.mm.EntranceX.dto.WatchLaterDto;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;


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
            return "redirect:/login";
        }
    }

    @GetMapping("/user-profile-update")
    public String user_update(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            return "user/user-update";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user-this-month")
    public String user_thismonth(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int)session.getAttribute("LoginUser");
            List<Event> events = userService.getEvents();
            for (Event event : events) {
                byte[]photoByte= Base64.getDecoder().decode(event.getEncodedPhoto().getBytes());
            }
            model.addAttribute("events", events);

            return "user/this-month";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user-promotion")
    public String user_promotion(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int)session.getAttribute("LoginUser");
            List<Event> events = userService.getEvents();
            for (Event event : events) {
                byte[]photoByte= Base64.getDecoder().decode(event.getEncodedPhoto().getBytes());
            }
            model.addAttribute("events", events);
            return "user/promotion";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user-trending")
    public String user_trending(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            return "user/trending";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user-upcoming")
    public String user_upcoming(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            return "user/upcoming";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user-history")
    public String user_history(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            return "user/history";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user-watch-later")
    public String user_watch_later(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            return "user/watch-later";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user-about")
    public String userAbout() {
        return "user/about";
    }

    @GetMapping("/user-help")
    public String userHelp() {
        return "user/help";
    }

    @GetMapping("/user-term")
    public String userTerm() {
        return "user/termCondition";
    }

    @GetMapping("/user-privacyPolicy")
    public String userPrivacyPolicy() {
        return "user/privacyPolicy";
    }

    @GetMapping("/user-contact")
    public String userContact() {
        return "user/contact";
    }

    @GetMapping(value = "/user-signup")
    public String userRegister() {
        return "login-signup/UserSignUp";
    }

    @PostMapping(value = "/user-signup")
    public String userRegisterPost(@ModelAttribute UserDto userDto, RedirectAttributes redirectAttributes) {
        if (userDao.findByUserName(userDto.getUserName()) == null && userDao.findByEmail(userDto.getEmail()) == null) {
            userService.createUser(userDto);
            return "redirect:/login";
        } else if (userDao.findByUserName(userDto.getUserName()) != null) {
            redirectAttributes.addAttribute("userNameExist", true);
            return "redirect:/user-signup";
        } else if (userDao.findByEmail(userDto.getEmail()) != null) {
            redirectAttributes.addAttribute("emailExist", true);
            return "redirect:/user-signup";
        }else{
            redirectAttributes.addAttribute("userNameExist", true);
            redirectAttributes.addAttribute("emailExist", true);
            return "redirect:/user-signup";
        }

    }
    @PostMapping("/user-watch-later-button")
    public String watchLater(HttpServletRequest request, @RequestParam int userId, int eventId){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            userService.saveEventToWatchLater(userId, eventId);
            return "redirect:/event-detail";
        }else {
            return "redirect:/login";
        }
    }
}
