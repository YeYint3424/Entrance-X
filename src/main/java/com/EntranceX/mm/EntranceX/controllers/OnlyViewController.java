package com.EntranceX.mm.EntranceX.controllers;

import com.EntranceX.mm.EntranceX.models.Artist;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.Event_Artist;
import com.EntranceX.mm.EntranceX.services.ArtistService;
import com.EntranceX.mm.EntranceX.services.EventService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class OnlyViewController {
    @Autowired
    EventService eventService;
    @Autowired
    ArtistService artistService;

    @GetMapping("/main-all-event")
    public String main_this_month(Model model) {
        List<Event> events = eventService.getEvents();
        for (Event event : events) {
            byte[] photo = Base64.getDecoder().decode(event.getEncodedPhoto());
        }
        model.addAttribute("events", events);
        return "event/all-event";
    }

    @GetMapping("/main-promotion")
    public String main_promotion(Model model) {
        List<Event> events = eventService.getEvents();
        for (Event event : events) {
            byte[] photo = Base64.getDecoder().decode(event.getEncodedPhoto());
        }
        model.addAttribute("events", events);
        return "event/promotion";
    }

    @GetMapping("/main-trending")
    public String main_trending(Model model) {
        List<Event> events = eventService.getEvents();
        for (Event event : events) {
            byte[] photo = Base64.getDecoder().decode(event.getEncodedPhoto());
        }
        model.addAttribute("events", events);
        return "event/trending";
    }

    @GetMapping("/main-search")
    public String main_search() {
        return "main/search-page";
    }

    @GetMapping("/main-event-detail")
    public String main_event_detail(HttpServletRequest request, @RequestParam("eventId") int eventId, Model model) {
        HttpSession session = request.getSession(false);

            Event eventDetails = eventService.showEventDetail(eventId);

            List<Event_Artist> eventArtists = eventDetails.getEventArtist();
            List<Artist> artists = new ArrayList<>();
            for (Event_Artist eventArtist : eventArtists) {
                int artistId = eventArtist.getArtist().getId();
                Artist artist = artistService.findById(artistId);
                artists.add(artist);
            }

            String eventTime = eventDetails.getStartTime() + " to " + eventDetails.getEndTime();
            byte[] decodedPhoto = Base64.getDecoder().decode(eventDetails.getEncodedPhoto().getBytes());

            model.addAttribute("artists", artists);
            model.addAttribute("eventDetails", eventDetails);
            model.addAttribute("eventTime", eventTime);


            return "main/event-detail";
        }
    }
