package com.EntranceX.mm.EntranceX.controllers;

import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dao.UserDao;
import com.EntranceX.mm.EntranceX.dto.OrganizerDto;
import com.EntranceX.mm.EntranceX.models.*;
<<<<<<< HEAD
import com.EntranceX.mm.EntranceX.services.*;
=======
import com.EntranceX.mm.EntranceX.services.ArtistService;
import com.EntranceX.mm.EntranceX.services.EventService;
import com.EntranceX.mm.EntranceX.services.OrderService;
import com.EntranceX.mm.EntranceX.services.OrganizerService;
>>>>>>> 43b4f512fd4c0772960a23754fd7a00e38f385ea
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
import java.util.ArrayList;
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

    @Autowired
    ArtistService artistService;
    
    @Autowired
    UserDao userDao;

    @Autowired
    OrderService orderService;
<<<<<<< HEAD

    @Autowired
    TicketQrService ticketQrService;




=======
    
>>>>>>> 43b4f512fd4c0772960a23754fd7a00e38f385ea
    @GetMapping("/org-page")
    public String org_home(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            int organizerId=(int)session.getAttribute("LoginOrganizer");
            List<Event> events=eventService.getEventsByOrganizerId(organizerId);
            model.addAttribute("events" , events);

            return "main/org-page";
        } else{
                return "redirect:/login";}
    }

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


    @GetMapping("/org-promotion")
    public String org_future(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            int organizerId=(int)session.getAttribute("LoginOrganizer");
            List<Event> events = eventService.getEventsByOrganizerId(organizerId);
            for (Event event : events) {
                byte[]photoByte=Base64.getDecoder().decode(event.getEncodedPhoto().getBytes());
            }
            model.addAttribute("events", events);
            model.addAttribute("localDate", LocalDate.now());
            return "org/promotion";
        }else{
            return "redirect:/login"; }
    }

    @GetMapping("/org-sale-record")
    public String org_Sale_Record(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            int organizerId=(int) session.getAttribute("LoginOrganizer");
            List<Event> events=eventService.getEventsByOrganizerId(organizerId);
            List<TicketOrder_History> ticketOrder=new ArrayList<>();
            for(Event event:events){
                for(TicketOrder_History ticketOrder_history: event.getOrderHistory()){
                    ticketOrder.add(ticketOrder_history);

                }
            }
            model.addAttribute("ticketOrder", ticketOrder);
            model.addAttribute("events",events);
        return "org/sale-record";
    }else{
            return "redirect:/login"; } }

    @GetMapping(value = "/org-signup")
    public String organizerRegister() {
        return "login-signup/OrganizerSignUp";
        }

    @PostMapping(value = "/org-signup")
    public String organizerRegisterPost(@ModelAttribute OrganizerDto organizerDto, Model model, RedirectAttributes redirectAttributes) {
        if (userDao.findByUserName(organizerDto.getUserName()) == null && userDao.findByEmail(organizerDto.getOrganizerEmail()) == null &&
                organizerDao.findByUserName(organizerDto.getUserName()) == null && organizerDao.findByOrganizerEmail(organizerDto.getOrganizerEmail()) == null) {
            organizerService.createOrganizer(organizerDto);
//            emailService.sendCode(organizerDto.getEmail());
            return "redirect:/login";
        } else if (userDao.findByUserName(organizerDto.getUserName()) != null || organizerDao.findByUserName(organizerDto.getUserName()) == null) {
            redirectAttributes.addAttribute("userNameExist", true);
            return "redirect:/org-signup";
        } else if (userDao.findByEmail(organizerDto.getOrganizerEmail()) != null || organizerDao.findByOrganizerEmail(organizerDto.getOrganizerEmail()) == null) {
            redirectAttributes.addAttribute("emailExist", true);
            return "redirect:/org-signup";
        }else {
            redirectAttributes.addAttribute("userNameExist", true);
            redirectAttributes.addAttribute("emailExist", true);
            return "redirect:/org-signup";
        }

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



    @GetMapping("/org-old-event")
        public String org_event(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            int organizerId=(int) session.getAttribute("LoginOrganizer");
            List<Event> events=eventService.getEventsByOrganizerId(organizerId);
            model.addAttribute("events", events);
            return "org/org-event-request&old-event";
        }else {
            return "redirect:/login";
        }
    }

        @GetMapping("/org-eventdetail")
        public String org_eventdetail(HttpServletRequest request, @RequestParam("eventId") int eventId, Model model){
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("LoginOrganizer") != null) {
                int orgId=(int) session.getAttribute("LoginOrganizer");
                Event eventDetails=eventService.showEventDetail(eventId);

                List<Event_Artist> eventArtists = eventDetails.getEventArtist();
                List<Artist> artists = new ArrayList<>();
                for (Event_Artist eventArtist : eventArtists) {
                    int artistId=eventArtist.getArtist().getId();
                    Artist artist = artistService.findById(artistId);
                    artists.add(artist);
                }

                String eventTime = eventDetails.getStartTime() + " to " + eventDetails.getEndTime();
                byte[] decodedPhoto = Base64.getDecoder().decode(eventDetails.getEncodedPhoto().getBytes());

                model.addAttribute("artists", artists);
                model.addAttribute("eventDetails",eventDetails);
                model.addAttribute("eventTime", eventTime);
                model.addAttribute("orgId", orgId);

                return "org/org-eventdetail";
            }else {
                return "redirect:/login";
            }
        }
<<<<<<< HEAD


=======
    @PostMapping("/event-remove")
    public String eventRemove(HttpServletRequest request, Model model, @RequestParam("eventId")int eventId) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            Event event = eventService.cancel(eventId, 4);
            return "redirect:/org-old-event";
        } else {
            return "redirect:/login";
        }

    }
>>>>>>> 43b4f512fd4c0772960a23754fd7a00e38f385ea
}
