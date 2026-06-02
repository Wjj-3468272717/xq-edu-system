package com.v1.controller;


import com.v1.pojo.Membertype;
import com.v1.service.MembertypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-02
 */
@Controller
@RequestMapping("/membertype")
public class MembertypeController {
    @Autowired
    MembertypeService membertypeService;

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("userName","eric");
        return "hello";
    }

    @RequestMapping("/list")
    public @ResponseBody List<Membertype> list(){
        List<Membertype> list = membertypeService.list();
        return list;
    }

}
