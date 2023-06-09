package com.EntranceX.mm.EntranceX.controllers;


import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.dao.Event_ArtistDao;
import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dto.EventArtistDto;

import com.EntranceX.mm.EntranceX.dto.TicketOrder_HistoryDto;
import com.EntranceX.mm.EntranceX.models.Artist;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.Event_Artist;
import com.EntranceX.mm.EntranceX.models.WatchLater;
import com.EntranceX.mm.EntranceX.services.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Controller
public class EventController {
    @Autowired
    EventDao eventDao;

    @Autowired
    OrganizerDao organizerDao;
    @Autowired
    EventService eventService;
    @Autowired
    OrderService orderService;
    @Autowired
    WatchLaterService watchLaterService;
    @Autowired
    ArtistService artistService;
    @Autowired
    Event_ArtistService event_artistService;

    @GetMapping("/event-register")
    public String registerEvent(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
        List<Artist> artists=artistService.getExistingArtists();
        model.addAttribute("artists", artists);
        return "event/event-register";
        }else {
            return "redirect:/login";
        }
    }

    @PostMapping(value = "/event-register")
    public String createEvent(@ModelAttribute EventArtistDto eventArtistDto, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginOrganizer") != null) {
            int organizerId=(int) session.getAttribute("LoginOrganizer");
            eventArtistDto.setRequestTime(LocalDateTime.now());


            Event event=eventService.createEvent(eventArtistDto, organizerId);
            artistService.addArtistForEvent(eventArtistDto, event.getId());
            if(eventArtistDto.getExistArtistId()!=null){
                artistService.addExistArtistForEvent(eventArtistDto, event.getId());}
        return "redirect:/org-page";
    }else {
            return "redirect:/login";
        }
    }

    @GetMapping("/event-detail")
    public String eventDetails(HttpServletRequest request, @RequestParam("eventId") int eventId,
                               @RequestParam("trending")int trending, Model model){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int) session.getAttribute("LoginUser");
            Event eventDetails=eventService.showEventDetail(eventId);
            eventService.trending(eventId, trending);
            List<Event_Artist> eventArtists = eventDetails.getEventArtist();
            List<Artist> artists = new ArrayList<>();
            for (Event_Artist eventArtist : eventArtists) {
                int artistId=eventArtist.getArtist().getId();
                Artist artist = artistService.findById(artistId);
                artists.add(artist);
            }

            String eventTime = eventDetails.getStartTime() + " to " + eventDetails.getEndTime();
            byte[] decodedPhoto = Base64.getDecoder().decode(eventDetails.getEncodedPhoto().getBytes());

            WatchLater watchLater=watchLaterService.findBySpecificUser(userId, eventId);

            if(watchLater!=null){
            int watch=1;
            model.addAttribute("watchLater", watch);
            }

            model.addAttribute("artists", artists);
            model.addAttribute("eventDetails",eventDetails);
            model.addAttribute("eventTime", eventTime);
            model.addAttribute("userId", userId);

        return "event/event-detail";
        }else {
            return "redirect:/login";
        }
    }
    @PostMapping("/order-payment")
    public String eventDetailsPost(HttpServletRequest request, Model model,
                                   @ModelAttribute TicketOrder_HistoryDto ticketOrder){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {

            model.addAttribute("ticketOrder", ticketOrder);
            Event eventDetails=eventService.showEventDetail(ticketOrder.getEventId());

            String eventTime = eventDetails.getStartTime() + " to " + eventDetails.getEndTime();
            byte[] decodedKpayQr = Base64.getDecoder().decode(eventDetails.getKpayQrEncoded().getBytes());
            byte[] decodedWavePayQr = Base64.getDecoder().decode(eventDetails.getWavepayQrEncoded().getBytes());
            model.addAttribute("eventDetails",eventDetails);
            model.addAttribute("eventTime", eventTime);
            return "event/order-payment";
        }else {
            return "redirect:/login";
        }
    }




    @PostMapping("/send-order-to-admin")
    public String orderPayment(HttpServletRequest request, @ModelAttribute TicketOrder_HistoryDto ticketOrderDto) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {


            orderService.orderRequest(ticketOrderDto);
        return "redirect:/user-voucher";}
        else {
        return "redirect:/login";
    }
}


}
