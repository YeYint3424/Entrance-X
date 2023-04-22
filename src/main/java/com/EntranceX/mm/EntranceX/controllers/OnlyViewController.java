package com.EntranceX.mm.EntranceX.controllers;

import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Base64;
import java.util.List;

@Controller
public class OnlyViewController {
    @Autowired
    EventService eventService;

    @GetMapping("/main-all-event")
    public String main_this_month(Model model){
        List<Event> events=eventService.getEvents();
        for(Event event: events){
            byte[]photo= Base64.getDecoder().decode(event.getEncodedPhoto());
        }
        model.addAttribute("events", events);
        return "event/all-event";
    }
    @GetMapping("/main-promotion")
    public String main_promotion(){
        return"event/promotion";
    }
    @GetMapping("/main-trending")
    public String main_trending(){
        return "event/trending";
    }

    @GetMapping("/main-search")
    public String main_search(){
        return "main/search-page";
    }

}
