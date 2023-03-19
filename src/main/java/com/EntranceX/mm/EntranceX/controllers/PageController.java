package com.EntranceX.mm.EntranceX.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String home(){
        return "main/home";
    }

}
