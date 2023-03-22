package com.EntranceX.mm.EntranceX.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OnlyViewController {

    @RequestMapping("/main-this-month")
    public String main_this_month(){
        return "event/this-month";
    }
    @RequestMapping("/main-promotion")
    public String main_promotion(){
        return"event/promotion";
    }
    @RequestMapping("/main-trending")
    public String main_trending(){
        return "event/trending";
    }
    @RequestMapping("/main-upcoming")
    public String main_upcoming(){
        return "event/upcoming";
    }

}
