package com.EntranceX.mm.EntranceX.controllers;

import com.EntranceX.mm.EntranceX.dao.OrganizerDao;
import com.EntranceX.mm.EntranceX.models.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrgController {
@Autowired
    OrganizerDao organizerDao;

    @GetMapping("/org-profile")
    public String org_profile(){return "org/org-profile";}

    @GetMapping("/org-profile-update")
    public String org_update(){return "org/org-update";}

    @GetMapping("/org-ongoing")
    public String org_ongoing(){
        return "org/ongoing";
    }
    @GetMapping("/org-future")
    public String org_future(){
        return "org/future";
    }
    @GetMapping("/org-sale-record")
    public String org_Sale_Record(){
        return "org/sale-record";
    }

    @GetMapping(value = "organizer/signup")
    public String organizerRegister() {
        return "login-signup/OrganizerSignUp";
    }

    @PostMapping(value = "organizer/signup")
    public String organizerRegisterPost(@RequestParam("userName") String userName, @RequestParam("organizerName") String organizerName, @RequestParam("organizerEmail") String organizerEmail,
                                        @RequestParam("organizerPhone") String organizerPhone, @RequestParam("companyName") String companyName,
                                        @RequestParam("companyEmail") String companyEmail, @RequestParam("companyPhone") String companyPhone,
                                        @RequestParam("companyAddress") String companyAddress, @RequestParam("companyBio") String companyBio,
                                        @RequestParam("password") String password) {

//
//        int orgPh=Integer.valueOf(organizerPhone);
//        int comPh=Integer.valueOf(companyPhone);
        Organizer organizer = new Organizer(userName, organizerName, companyName, organizerEmail, organizerPhone, companyEmail, companyPhone,companyAddress,   companyBio, password);
        organizer.setUserName(userName);
        organizerDao.save(organizer);
        return "redirect:/login";
    }
}
