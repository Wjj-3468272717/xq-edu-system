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
        return "member-type.html";
    }

}
