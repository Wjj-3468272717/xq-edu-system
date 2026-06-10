package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.v1.pojo.AdminRoleMenus;
import com.v1.service.AdminRoleMenusService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/admin-role-menus")
public class AdminRoleMenusController {

    @Autowired
    AdminRoleMenusService adminRoleMenusService;

    @GetMapping("queryByRoleId/{id}")
    public DataResults queryByRoleId(@PathVariable("id") Integer id){
        List<AdminRoleMenus> list = adminRoleMenusService.list(new QueryWrapper<AdminRoleMenus>().eq("roleId", id));
        return DataResults.success(ResultCode.SUCCESS,list);
    }


}
