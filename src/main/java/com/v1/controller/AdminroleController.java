package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.v1.pojo.Adminrole;
import com.v1.service.AdminroleService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-10
 */
@RestController
@RequestMapping("/adminrole")
public class AdminroleController {

    @Autowired
    AdminroleService adminroleService;

    @GetMapping("/list")
    public DataResults list(){
    List<Adminrole> list = adminroleService.list(new QueryWrapper<Adminrole>().eq("del",0));
        return DataResults.success(ResultCode.SUCCESS,list);
    }

}
