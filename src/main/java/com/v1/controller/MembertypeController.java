package com.v1.controller;


import com.v1.pojo.Membertype;
import com.v1.service.MembertypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RestController
@RequestMapping("/membertype")
public class MembertypeController {
    @Autowired
    MembertypeService membertypeService;

    @RequestMapping("/list")
    public List<Membertype> list(){
        List<Membertype> list = membertypeService.list();
        return list;
    }

}
