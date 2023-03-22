package com.EntranceX.mm.EntranceX.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrgController {

    @RequestMapping("/org-ongoing")
    public String org_ongoing(){
        return "org/ongoing";
    }
    @RequestMapping("/org-future")
    public String org_future(){
        return "org/future";
    }
    @RequestMapping("/org-sale-record")
    public String org_Sale_Record(){
        return "org/sale-record";
    }
}
