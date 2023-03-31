package com.EntranceX.mm.EntranceX.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

    @RequestMapping("/event-register")
    public String registerEvent(){return "event/event-register";}

    @RequestMapping("/event-approve")
    public String eventApprove(){
        return "admin/event-approve";
    }
    @RequestMapping("/voucher-approve")
    public String voucherApprove(){
        return "admin/voucher-approve";
    }
    @RequestMapping("/unban")
    public String unBan(){
        return "admin/unban";
    }
}
