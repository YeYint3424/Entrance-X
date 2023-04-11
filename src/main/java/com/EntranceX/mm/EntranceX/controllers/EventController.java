package com.EntranceX.mm.EntranceX.controllers;


import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dto.EventDto;

import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.services.EventService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
public class EventController {
    @Autowired
    EventDao eventDao;

    @Autowired
    OrganizerDao organizerDao;
    @Autowired
    EventService eventService;

    @GetMapping("/event-register")
    public String registerEvent(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
        return "event/event-register";
        }else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = "/event-register")
    public String createEvent(@ModelAttribute EventDto eventDto, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            int organizerId=(int) session.getAttribute("LoginOrganizer");
        eventService.createEvent(eventDto, organizerId);

        return "redirect:/org-page";
    }else {
            return "redirect:/login";
        }
    }

    @GetMapping("/event-detail")
    public String eventDetails(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
        return "event/event-detail";
        }else {
            return "redirect:/login";
        }
    }

    @GetMapping("/order-payment")
    public String orderPayment(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
        return "event/order-payment";}
        else {
        return "redirect:/login";
    }
}
}
