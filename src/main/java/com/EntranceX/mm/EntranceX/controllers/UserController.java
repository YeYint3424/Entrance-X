package com.EntranceX.mm.EntranceX.controllers;


import com.EntranceX.mm.EntranceX.config.QRCodeGenerator;
import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dao.UserDao;
import com.EntranceX.mm.EntranceX.dto.UserDto;
import com.EntranceX.mm.EntranceX.models.*;
import com.EntranceX.mm.EntranceX.services.*;
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




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



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

    @Autowired
    EmailService emailService;

    @Autowired
    OrganizerDao organizerDao;

    @Autowired
    TicketQrService ticketQrService;


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
        if (userDao.findByUserName(userDto.getUserName()) == null && userDao.findByEmail(userDto.getEmail()) == null &&
                organizerDao.findByUserName(userDto.getUserName()) == null && organizerDao.findByOrganizerEmail(userDto.getEmail()) == null) {
            userService.createUser(userDto);
//            emailService.sendCode(userDto.getEmail());
            return "redirect:/login";
        } else if (userDao.findByUserName(userDto.getUserName()) != null || organizerDao.findByUserName(userDto.getUserName()) == null) {
            redirectAttributes.addAttribute("userNameExist", true);
            return "redirect:/user-signup";
        } else if (userDao.findByEmail(userDto.getEmail()) != null || organizerDao.findByOrganizerEmail(userDto.getEmail()) == null) {
            redirectAttributes.addAttribute("emailExist", true);
            return "redirect:/user-signup";
        }else {
            redirectAttributes.addAttribute("userNameExist", true);
            redirectAttributes.addAttribute("emailExist", true);
            return "redirect:/user-signup";
        }

    }
    @PostMapping("/watch-later-add")
    public String watchLaterAdd(HttpServletRequest request,
                                @RequestParam("eventId")int eventId){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int) session.getAttribute("LoginUser");
            System.out.println(userId);
            System.out.println(eventId);
            watchLaterService.saveEventToWatchLater(userId, eventId);
            int trending=0;
            return String.format("redirect:/event-detail?eventId=%d&trending=%d", eventId, trending);
        }else {
            return "redirect:/login";
        }
    }

    @PostMapping("/watch-later-remove")
    public String watchLaterRemove(HttpServletRequest request,
                                   @RequestParam("eventId")int eventId){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int) session.getAttribute("LoginUser");
            watchLaterService.removeEventFromWatchLater(userId, eventId);
            return "redirect:/user-watch-later";

        }else {
            return "redirect:/login";
        }
    }
    @GetMapping("/ticket-voucher")
    public String ticketVoucher(HttpServletRequest request, Model model,
                                @RequestParam("eventId")int eventId, @RequestParam("orderId")int orderId) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int) session.getAttribute("LoginUser");


            TicketOrder_History ticketOrder=orderService.getSpecificTicketForUser(orderId, userId, eventId);
            List<TicketQr> ticketQrs = ticketQrService.findByOrderId(ticketOrder.getId());
            for(TicketQr ticketQr:ticketQrs){
                byte[] qr=Base64.getDecoder().decode(ticketQr.getTicketQr().getBytes());
            }
            model.addAttribute("ticketQr", ticketQrs);
            model.addAttribute("ticketOrder", ticketOrder);
            return "user/ticketVoucher";
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/user-all-event")
    public String user_all_event(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
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
    @GetMapping("/user-watch-later")
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
    public String user_voucher(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int) session.getAttribute("LoginUser");

            List<TicketOrder_History>ticketOrder=orderService.getUserOrderList(userId);
            model.addAttribute("ticketOrder", ticketOrder);
        return "user/voucher";
        }else {
            return "redirect:/login";
        }
    }
    @PostMapping("/order-remove")
    public String eventRemove(HttpServletRequest request, Model model, @RequestParam("orderId")int orderId) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            TicketOrder_History ticketOrder=orderService.cancel(orderId, 4);
            return "redirect:/user-voucher";
        } else {
            return "redirect:/login";
        }

    }


}
