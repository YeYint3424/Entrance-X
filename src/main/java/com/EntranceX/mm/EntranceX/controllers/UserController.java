package com.EntranceX.mm.EntranceX.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {


    @GetMapping("/user-this-month")
    public String user_thismonth(){ return "user/this-month"; }
    @GetMapping("/user-promotion")
    public String user_promotion(){
        return "user/promotion";
    }
    @GetMapping("/user-trending")
    public String user_trending(){
        return "user/trending";
    }
    @GetMapping("/user-upcoming")
    public String user_upcoming(){
        return "user/upcoming";
    }
    @GetMapping("/user-history")
    public String user_history(){
        return "user/history";
    }
    @GetMapping("/user-watch-later")
    public String user_watch_later(){
        return "user/watch-later";
    }

    @GetMapping("/user-about")
    public String userAbout(){
        return "user/about";
    }
    @GetMapping("/user-help")
    public String userHelp(){
        return "user/help";
    }
    @GetMapping("/user-term")
    public String userTerm(){
        return "user/termCondition";
    }
    @GetMapping("/user-privacyPolicy")
    public String userPrivacyPolicy(){
        return "user/privacyPolicy";
    }
    @GetMapping("/user-contact")
    public String userContact(){
        return "user/contact";
    }


}
