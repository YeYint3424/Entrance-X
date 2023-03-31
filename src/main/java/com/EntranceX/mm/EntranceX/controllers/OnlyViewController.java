package com.EntranceX.mm.EntranceX.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OnlyViewController {

    @GetMapping("/main-this-month")
    public String main_this_month(){
        return "event/this-month";
    }
    @GetMapping("/main-promotion")
    public String main_promotion(){
        return"event/promotion";
    }
    @GetMapping("/main-trending")
    public String main_trending(){
        return "event/trending";
    }
    @GetMapping("/main-upcoming")
    public String main_upcoming(){
        return "event/upcoming";
    }

}
