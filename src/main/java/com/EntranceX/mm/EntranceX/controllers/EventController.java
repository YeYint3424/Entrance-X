package com.EntranceX.mm.EntranceX.controllers;


import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dto.EventDto;

import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.services.EventService;

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
    public String registerEvent(HttpSession session){
        session.getAttribute("LoginOrganizer");
        return "event/event-register";}

    @PostMapping(value = "/event-register")
    public String createEvent(@ModelAttribute EventDto eventDto, Model model) throws IOException {
        eventService.createEvent(eventDto);
        model.addAttribute("message", "Event created successfully!");
        return "redirect:/org-page";
    }

    @GetMapping("/event-detail")
    public String eventDetails(){return "event/event-detail";}

    @GetMapping("/order-payment")
    public String orderPayment(){return "event/order-payment";}
}
