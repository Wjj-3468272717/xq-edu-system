package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.v1.pojo.AdminUserRole;
import com.v1.service.AdminUserRoleService;
import com.v1.service.AdminuserService;
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
@RequestMapping("/admin-user-role")
public class AdminUserRoleController {

    @Autowired
    AdminUserRoleService adminUserRoleService;

    @GetMapping("queryByAdminId/{adminId}")
    public DataResults queryByAdminId(@PathVariable("adminId") Integer adminId){
        QueryWrapper<AdminUserRole> q = new QueryWrapper<>();
        q.eq("adminId",adminId);
        List<AdminUserRole> list = adminUserRoleService.list(q);
        return DataResults.success(ResultCode.SUCCESS,list);
    }

}
