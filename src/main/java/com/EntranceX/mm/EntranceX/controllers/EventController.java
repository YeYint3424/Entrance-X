package com.EntranceX.mm.EntranceX.controllers;


import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.dto.EventDto;

import com.EntranceX.mm.EntranceX.dto.TicketOrder_HistoryDto;
import com.EntranceX.mm.EntranceX.models.Event;
import com.EntranceX.mm.EntranceX.models.TicketOrder_History;
import com.EntranceX.mm.EntranceX.models.WatchLater;
import com.EntranceX.mm.EntranceX.services.EventService;

import com.EntranceX.mm.EntranceX.services.OrderService;
import com.EntranceX.mm.EntranceX.services.WatchLaterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @GetMapping("/event-register")
    public String registerEvent(HttpServletRequest request, Model model){
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
            eventDto.setRequestTime(LocalDateTime.now());
            eventService.createEvent(eventDto, organizerId);

        return "redirect:/org-page";
    }else {
            return "redirect:/login";
        }
    }

    @GetMapping("/event-detail")
    public String eventDetails(HttpServletRequest request, @RequestParam("eventId") int eventId, Model model){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginUser") != null) {
            int userId=(int) session.getAttribute("LoginUser");
            Event eventDetails=eventService.showEventDetail(eventId);
            String eventTime = eventDetails.getStartTime() + " to " + eventDetails.getEndTime();
            byte[] decodedPhoto = Base64.getDecoder().decode(eventDetails.getEncodedPhoto().getBytes());


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

            System.out.println(ticketOrderDto);
            orderService.orderRequest(ticketOrderDto);
        return "redirect:/user-history";}
        else {
        return "redirect:/login";
    }
}


}
