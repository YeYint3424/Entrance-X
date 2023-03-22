package com.EntranceX.mm.EntranceX.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {


    @RequestMapping("/user-this-month")
    public String user_thismonth(){
        return "user/this-month";
    }
    @RequestMapping("/user-promotion")
    public String user_promotion(){
        return "user/promotion";
    }
    @RequestMapping("/user-trending")
    public String user_trending(){
        return "user/trending";
    }
    @RequestMapping("/user-upcoming")
    public String user_upcoming(){
        return "user/upcoming";
    }
    @RequestMapping("/user-history")
    public String user_history(){
        return "user/history";
    }
    @RequestMapping("/user-watch-later")
    public String user_watch_later(){
        return "user/watch-later";
    }

}
