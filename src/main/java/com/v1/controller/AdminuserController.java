package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.v1.pojo.Adminuser;
import com.v1.service.AdminuserService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-09
 */
@RestController
@RequestMapping("/adminuser")
public class AdminuserController {

    @Autowired
    AdminuserService adminuserService;

    @PostMapping("updatePwd")
    public DataResults updatepwd(String oldPassword, String newPassword, String newPasswordAgain, HttpSession session){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //从session中获取用户信息
        Adminuser user = (Adminuser)session.getAttribute("loginUser");
        boolean matches = bCryptPasswordEncoder.matches(oldPassword, user.getAdminPassword());
        //原密码是否正确
        if(matches){
            //新密码和旧密码不相同
            if(!oldPassword.equals(newPassword)){
                //两次密码相同
                if(newPassword.equals(newPasswordAgain)){
                    user.setAdminPassword(bCryptPasswordEncoder.encode(newPassword));
                    adminuserService.updateById(user);
                    //session失效
                    session.invalidate();
                    return DataResults.success(ResultCode.SUCCESS);
                }else{//两次密码不相同
                    return DataResults.success(ResultCode.REPASSWORD_ERROR);
                }
            }else{//新旧密码相同
                return DataResults.success(ResultCode.SAME_PASSWORD);
            }
        }else{//原密码错误
            return DataResults.success(ResultCode.PASSWORD_ERROR);
        }
    }

    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageSize, Integer pageNumber, String adminName){
        Map<String,Object> resultMap = new HashMap<>();
        QueryWrapper<Adminuser> q = new QueryWrapper<>();
        q.like(StringUtils.isNotEmpty(adminName),"adminName",adminName);
        q.eq("del",0);
        IPage<Adminuser> page = adminuserService.page(new Page<Adminuser>(pageNumber, pageSize), q);
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",page.getRecords());
        return resultMap;
    }

}
