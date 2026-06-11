package com.v1.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

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

//    @RolesAllowed(value = {"ROLE_管理员","ROLE_前台","ROLE_教师"})
//    @Secured(value = {"ROLE_管理员","ROLE_前台","ROLE_教师"})
    @PreAuthorize(value = "hasAnyRole('ROLE_管理员','ROLE_前台','ROLE_教师')")
    @RequestMapping({"index.html","/"})
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

    @RequestMapping("memeber-expire.html")
    public String toMemberExpire(){
        return "memeber-expire";
    }

    @RequestMapping("member-charge.html")
    public String toMemberCharge(){
        return "member-charge";
    }

    @RequestMapping("member-charge-records.html")
    public String toMemberChargeRecords(){
        return "member-charge-records";
    }

    @RequestMapping("member-card.html")
    public String toMemberCar(){
        return "member-card";
    }

    @RequestMapping("member-cardextend-records.html")
    public String toMemberCarRecords(){
        return "member-cardextend-records";
    }

    @RequestMapping("coach.html")
    public String toCoach(){
        return "coach";
    }

    @RequestMapping("coach-subject.html")
    public String toCoachSubject(){
        return "coach-subject";
    }

    //    @RolesAllowed(value = {"ROLE_管理员","ROLE_教师"})
//    @Secured(value ={"ROLE_管理员","ROLE_教师"})
    @PreAuthorize(value = "hasAnyRole('ROLE_管理员','ROLE_教师')")
    @RequestMapping("goods-list.html")
    public String toGoodsList(){
        return "goods-list";
    }

    @RequestMapping("goods-sales.html")
    public String toGoodsSales(){
        return "goods-sales";
    }

    @RequestMapping("loss.html")
    public String toLoos(){
        return "loos";
    }

    @RequestMapping("statistics.html")
    public String toStatistics(){
        return "statistics";
    }

    @RequestMapping("login.html")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("updpassword.html")
    public String toUpdPassword(){
        return "updpassword";
    }

    @RequestMapping("adminusers.html")
    public String toUsersPage(){
        return "adminusers";
    }

    @RequestMapping("adminroles.html")
    public String toAdminRoles(){
        return "adminroles";
    }

    @GetMapping("/403.html")
    public String to403(){
        return "403";
    }

}
