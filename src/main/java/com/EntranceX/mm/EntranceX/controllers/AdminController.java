package com.EntranceX.mm.EntranceX.controllers;

import com.EntranceX.mm.EntranceX.config.QRCodeGenerator;
import com.EntranceX.mm.EntranceX.dao.TicketDao;
import com.EntranceX.mm.EntranceX.dto.TicketDto;
import com.EntranceX.mm.EntranceX.models.*;
import com.EntranceX.mm.EntranceX.services.*;
import com.google.zxing.WriterException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    OrganizerService organizerService;

    @Autowired
    EventService eventService;

    @Autowired
    OrderService orderService;

    @Autowired
    ArtistService artistService;

    @GetMapping("/admin")
    public String admin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            List<Event> events = eventService.getEvents();
            List<Event> unApproveEvent=eventService.getUnApproveEvent(0);
            List<User> users = userService.getAllUserList();
            List<Organizer> organizers = organizerService.getAllOrganizerList();
            List<TicketOrder_History> vouchers = orderService.getUnApproveOrder(0);

            model.addAttribute("eventList", events);
            model.addAttribute("userCount", users);
            model.addAttribute("organizerCount", organizers);
            model.addAttribute("unApproveVoucher", vouchers);
            model.addAttribute("unApproveEvent", unApproveEvent);
            return "admin/admin";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/admin-eventdetail")
    public String admin_eventdetail(HttpServletRequest request, @RequestParam("eventId") int eventId, Model model){
        HttpSession session = request.getSession(false);
        if(session!=null && session.getAttribute("LoginAdmin")!=null){
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

            return "admin/admin-eventdetail";
        }else{
            return "redirect:/login";
        }
        }


    @GetMapping("/event-approve")
    public String eventApprove(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("LoginAdmin") != null) {
            List<Event> events = eventService.getEvents();
            model.addAttribute("eventList", events);
            return "admin/event-approve";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/unban")
    public String unban(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            return "admin/unban";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/voucher-approve")
    public String voucherApprove(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            List<TicketOrder_History> ticketOrder = orderService.getAllOrder();
            for (TicketOrder_History ticketOrderHistory : ticketOrder) {
                byte[] photoByte = Base64.getDecoder().decode(ticketOrderHistory.getEncodedPaymentScreenShot().getBytes());
            }
            model.addAttribute("ticketOrderList", ticketOrder);
            return "admin/voucher-approve";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/userList")
    public String userList(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            List<User> user = userService.getAllUserList();
            model.addAttribute("userList", user);
            return "admin/userList";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/orgList")
    public String orgList(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            List<Organizer> organizer = organizerService.getAllOrganizerList();
            model.addAttribute("organizerList", organizer);
            return "admin/orgList";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin-voucher-approve")
    public String voucherApproved(HttpServletRequest request, Model model,
                                  @RequestParam("voucherId")int voucherId, TicketDto ticketDto) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            TicketOrder_History ticketOrder=orderService.getOrderWithId(voucherId);
            orderService.approve(voucherId, 1);
            orderService.decreaseAvailableTicket(ticketOrder.getStandardTicketSold(),
                    ticketOrder.getVipTicketSold(), ticketOrder.getVvipTicketSold(), voucherId);
            return "redirect:/voucher-approve";}
        else {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin-voucher-cancel")
    public String voucherCancel(HttpServletRequest request, Model model,
                                  @RequestParam("voucherId")int voucherId) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            TicketOrder_History ticketOrder=orderService.getOrderWithId(voucherId);
            orderService.approve(voucherId, 2);

            return "redirect:/voucher-approve";}
        else {
            return "redirect:/login";
        }


    }

    @PostMapping("/admin-voucher-reject")
    public String voucherReject(HttpServletRequest request, Model model,
                                @RequestParam("voucherId")int voucherId) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            TicketOrder_History ticketOrder=orderService.getOrderWithId(voucherId);
            orderService.approve(voucherId, 3);

            return "redirect:/voucher-approve";}
        else {
            return "redirect:/login";
        }


    }

    @PostMapping("/admin-event-approve")
    public String eventApproved(HttpServletRequest request, Model model, @RequestParam("eventId")int eventId){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            Event event=eventService.approve(eventId, 1);
            return "redirect:/admin";
        }else {
            return "redirect:/login";
        }
    }
    @PostMapping("/admin-event-approve-2")
    public String eventApproved2(HttpServletRequest request, Model model, @RequestParam("eventId")int eventId){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            Event event=eventService.approve(eventId, 1);
            return "redirect:/event-approve";
        }else {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin-event-cancel")
    public String eventCanceled(HttpServletRequest request, Model model, @RequestParam("eventId")int eventId){
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            Event event=eventService.cancel(eventId, 2);
            return "redirect:/admin";
        }else {
            return "redirect:/login";
        }
    }

    @PostMapping("/admin-event-cancel-2")
    public String eventCanceled2(HttpServletRequest request, Model model, @RequestParam("eventId")int eventId) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            Event event = eventService.cancel(eventId, 2);
            return "redirect:/event-approve";
        } else {
            return "redirect:/login";
        }

    }
        @GetMapping("/admin-voucher-detail")
        public String voucher_detail (HttpServletRequest request, @RequestParam("userId") int userid, @RequestParam("orderId")int orderId,
                                      @RequestParam("eventId")int eventId, Model model) {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("LoginAdmin") != null) {
                TicketOrder_History ticketOrder=orderService.getSpecificTicketForUser(orderId, userid,eventId);
                model.addAttribute("ticketOrder", ticketOrder);

                return "admin/voucher-detail";
            } else {
                return "redirect:/login";
            }
        }
    @PostMapping("/admin-event-reject")
    public String eventRejected(HttpServletRequest request, Model model, @RequestParam("eventId")int eventId) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            Event event = eventService.cancel(eventId, 3);
            return "redirect:/admin";
        } else {
            return "redirect:/login";
        }

    }
    @PostMapping("/admin-event-reject-2")
    public String eventRejected2(HttpServletRequest request, Model model, @RequestParam("eventId")int eventId) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("LoginAdmin") != null) {
            Event event = eventService.cancel(eventId, 3);
            return "redirect:/event-approve";
        } else {
            return "redirect:/login";
        }

    }
        }

