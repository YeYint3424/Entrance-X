package com.EntranceX.mm.EntranceX.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String main(){
        return "main/home";
    }

    @RequestMapping("/user-page")
    public String user_home(){
        return"main/user-page";
    }

    @RequestMapping("/org-page")
    public String org_home(){
        return"main/org-page";
    }

    @RequestMapping("/about")
    public String about(){
        return "main/about";
    }
    @RequestMapping("/contact")
    public String contact(){
        return "main/contact";
    }
    @RequestMapping("/help")
    public String help(){
        return "main/help";
    }
    @RequestMapping("/privacypolicy")
    public String privacypolicy(){
        return "main/privacyPolicy";
    }
    @RequestMapping("/termsandconditions")
    public String terms(){
        return "main/termCondition";
    }
    @RequestMapping("/ticket-voucher")
    public String ticketVoucher(){
        return "main/ticketVoucher";
    }


}


