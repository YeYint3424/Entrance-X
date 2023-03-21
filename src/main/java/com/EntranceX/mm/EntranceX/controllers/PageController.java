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

    @RequestMapping("/main-promotion")
    public String main_promotion(){
        return"event/promotion";
    }
}


