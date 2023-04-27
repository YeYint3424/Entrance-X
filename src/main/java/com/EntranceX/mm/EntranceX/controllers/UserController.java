package com.EntranceX.mm.EntranceX.controllers;


import com.EntranceX.mm.EntranceX.config.QRCodeGenerator;
import com.EntranceX.mm.EntranceX.dao.UserDao;

import com.EntranceX.mm.EntranceX.dto.UserDto;

import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.TicketOrder_History;
import com.EntranceX.mm.EntranceX.models.User;
import com.EntranceX.mm.EntranceX.models.WatchLater;
import com.EntranceX.mm.EntranceX.services.EventService;
import com.EntranceX.mm.EntranceX.services.OrderService;
import com.EntranceX.mm.EntranceX.services.UserService;
import com.EntranceX.mm.EntranceX.services.WatchLaterService;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
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

    @Autowired
    EventService eventService;

    @Autowired
    WatchLaterService watchLaterService;

    @Autowired
    QRCodeGenerator qrCodeGenerator;

    @Autowired
    OrderService orderService;


    @GetMapping("/user-profile")
    public String user_profile(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int)session.getAttribute("LoginUser");
            User userData=userService.getUserData(userId);
            model.addAttribute("userData", userData);
            return "user/user-profile";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user-profile-update")
    public String userUpdate(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int)session.getAttribute("LoginUser");
            User userData=userService.getUserData(userId);
            model.addAttribute("userEdit", userData);
            return "user/user-update";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/user-profile-update")
    public String userUpdatePost(HttpServletRequest request, Model model, @RequestParam("oldPassword") String oldPassword,
                                 UserDto userDto, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int)session.getAttribute("LoginUser");
            User userData=userService.getUserData(userId);
            model.addAttribute("userData", userData);
            if(passwordEncoder.matches(oldPassword, userData.getPassword()) && !passwordEncoder.matches(userDto.getPassword(),userData.getPassword())){
                userService.editProfile(userDto, userId);
                return "redirect:/user-profile";
            }else if(passwordEncoder.matches(userDto.getPassword(),userData.getPassword())){
                redirectAttributes.addAttribute("alreadyUse", true);
                return "redirect:/user-profile-update";
            }else{
                redirectAttributes.addAttribute("error", true);
                return "redirect:/user-profile-update";
            }

        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/user-this-month")
    public String user_thismonth(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            List<Event> events = eventService.getEvents();
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
            List<Event> events = eventService.getEvents();
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
    public String user_trending(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int)session.getAttribute("LoginUser");
            List<Event> events = eventService.getEvents();
            for (Event event : events) {
                byte[]photoByte= Base64.getDecoder().decode(event.getEncodedPhoto().getBytes());
            }
            model.addAttribute("events", events);
            return "user/trending";
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/user-history")
    public String user_history(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int)session.getAttribute("LoginUser");
            List<TicketOrder_History> ticketOrder=orderService.getUserOrderList(userId);
            model.addAttribute("userOrderList", ticketOrder);
            return "user/history";
        } else {
            return "redirect:/login";
        }
    }



    @GetMapping("/user-about")
    public String userAbout() {
        return "user/about";
    }

    @GetMapping("/user-faq")
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
    @PostMapping("/watch-later-add")
    public String watchLaterAdd(HttpServletRequest request, @RequestParam("userId")int userId,
                                @RequestParam("eventId")int eventId){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            System.out.println(userId);
            System.out.println(eventId);
            watchLaterService.saveEventToWatchLater(userId, eventId);
            return String.format("redirect:/event-detail?eventId=%d", eventId);
        }else {
            return "redirect:/login";
        }
    }

    @PostMapping("/watch-later-remove")
    public String watchLaterRemove(HttpServletRequest request, @RequestParam("userId")int userId,
                                   @RequestParam("eventId")int eventId){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            watchLaterService.removeEventFromWatchLater(userId, eventId);
            return String.format("redirect:/event-detail?eventId=%d", eventId);

        }else {
            return "redirect:/login";
        }
    }
    @GetMapping("/ticket-voucher")
    public String ticketVoucher(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {


            return "main/ticketVoucher";
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/user-all-event")
    public String user_all_event(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int)session.getAttribute("LoginUser");
            List<Event> events = eventService.getEvents();
            for (Event event : events) {
                byte[]photoByte= Base64.getDecoder().decode(event.getEncodedPhoto().getBytes());
            }
            model.addAttribute("events", events);
            return "user/all-event";
        }else {
            return "redirect:/login";
        }

    }
    @GetMapping("user-watch-later")
    public String watchLater(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int) session.getAttribute("LoginUser");
            List<WatchLater> watchLaterEvents=watchLaterService.findByUser(userId);
            model.addAttribute("watchLaterEvents", watchLaterEvents);
            model.addAttribute("localDate", LocalDate.now());
            return "user/watch-later";
        }else {
            return "redirect:/login";
        }
    }

    @GetMapping("/user-voucher")
    public String user_voucher(){
        return "user/voucher";
    }
}
