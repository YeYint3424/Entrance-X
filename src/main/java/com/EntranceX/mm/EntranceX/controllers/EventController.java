package com.EntranceX.mm.EntranceX.controllers;

import com.EntranceX.mm.EntranceX.dao.EventDao;
import com.EntranceX.mm.EntranceX.models.Event;
import jakarta.annotation.PostConstruct;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Controller
public class EventController {
@Autowired
    EventDao eventDao;

    @GetMapping("/event-register")
    public String registerEvent(){return "event/event-register";}

    @PostMapping("/event-register")
    public String registerEventPost(@RequestParam("eventName") String eventName, @RequestParam("time") String time, @RequestParam("date")LocalDate date,
                                    @RequestParam("venue") String venue, @RequestParam("artist")String artist, @RequestParam("eventPhoto") MultipartFile eventPhoto,
                                    @RequestParam("standardTicketQuantity")int standardTicketQuantity, @RequestParam("standardTicketPrice")int standardTicketPrice,
                                    @RequestParam("vipTicketQuantity")int vipTicketQuantity, @RequestParam("vipTicketPrice")int vipTicketPrice,
                                    @RequestParam("vvipTicketQuantity")int vvipTicketQuantity, @RequestParam("vvipTicketPrice")int vvipTicketPrice,
                                    @RequestParam("promotion")int promotion, @RequestParam("shippingCost")int shippingCost, @RequestParam("paymentMethod")String paymentMethod,
                                    @RequestParam("eventDescription")String eventDescription) throws IOException {


        // Encode the byte array to a Base64 string
         String encodedPhoto = Base64.encodeBase64String(eventPhoto.getBytes());

//        String stp=String.valueOf(standardTicketPrice);
//        String vtp=String.valueOf(vipTicketPrice);
//        String vvtp=String.valueOf(vvipTicketPrice);
//        String stq=String.valueOf(standardTicketQuantity);
//        String vtq=String.valueOf(vipTicketQuantity);
//        String vvtq=String.valueOf(vvipTicketQuantity);



        Event event= new Event(eventName, venue, artist, promotion, paymentMethod, eventDescription, shippingCost, standardTicketPrice, standardTicketQuantity,vipTicketPrice, vipTicketQuantity,vvipTicketPrice,vvipTicketQuantity,date,time,eventPhoto.getBytes(),encodedPhoto.getBytes());

        eventDao.save(event);

        return "redirect:/org-page";
    }

    @GetMapping("/event-detail")
    public String eventDetails(){return "event/event-detail";}
}
