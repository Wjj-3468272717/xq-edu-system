package com.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

    /**
     * 跳转到member-type.html
     * @return
     */
    @RequestMapping("member-type.html")
    public String toMemberType(){
        return "member-type";
    }

    @RequestMapping("equipment.html")
    public String toEquipment(){
        return "equipment";
    }

    @RequestMapping("subject.html")
    public String toSubject(){
        return "subject";
    }

    @RequestMapping("index.html")
    public String toIndexPage(){
        return "index";
    }

    @RequestMapping("home.html")
    public String toHomePage(){
        return "home";
    }

    @RequestMapping("member.html")
    public String toMemberPage(){
        return "member";
    }

}
