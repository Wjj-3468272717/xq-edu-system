package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.v1.pojo.Coach;
import com.v1.pojo.Member;
import com.v1.pojo.Privatecoachinfo;
import com.v1.pojo.Subject;
import com.v1.service.CoachService;
import com.v1.service.MemberService;
import com.v1.service.PrivatecoachinfoService;
import com.v1.service.SubjectService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-05
 */
@RestController
@RequestMapping("/privatecoachinfo")
public class PrivatecoachinfoController {

    @Autowired
    PrivatecoachinfoService privatecoachinfoService;
    @Autowired
    MemberService memberService;
    @Autowired
    CoachService coachService;
    @Autowired
    SubjectService subjectService;

    @GetMapping("/queryPage")
    public Map<String, Object> queryPage(Integer pageNumber, Integer pageSize, String coachName, String memberid, String subname) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageStart", (pageNumber - 1) * pageSize);
        paramMap.put("pageSize", pageSize);
        paramMap.put("coachName", coachName);
        paramMap.put("memberid", memberid);
        paramMap.put("subname", subname);

        int total = privatecoachinfoService.totalCount(paramMap);
        List<Privatecoachinfo> rows = privatecoachinfoService.queryPage(paramMap);
        resultMap.put("total", total);
        resultMap.put("rows", rows);
        return resultMap;
    }

    @GetMapping("getDetailsById/{idd}")
    public List<Privatecoachinfo> getDetailsById(@PathVariable("idd") Integer idd) {
        Privatecoachinfo privatecoachinfo = privatecoachinfoService.getById(idd);
        Member member = memberService.getById(privatecoachinfo.getMemberid());
        Coach coach = coachService.getById(privatecoachinfo.getCoachid());
        Subject subject = subjectService.getById(privatecoachinfo.getSubjectid());
        privatecoachinfo.setMember(member);
        privatecoachinfo.setCoach(coach);
        privatecoachinfo.setSubject(subject);
        List<Privatecoachinfo> list = new ArrayList<>();
        list.add(privatecoachinfo);
        return list;
    }

    @GetMapping("count")
    public DataResults count(Integer memid) {
        int count = privatecoachinfoService.count(new QueryWrapper<Privatecoachinfo>().eq("del", 0).eq("memberid", memid));
        return DataResults.success(ResultCode.SUCCESS, count);
    }

    @PostMapping("add")
    public DataResults add(Privatecoachinfo privatecoachinfo) {
        privatecoachinfo.setDel(0);
        //TODO: 后期设置为系统登录用户
        privatecoachinfo.setAdmin("匿名");
        privatecoachinfoService.baoke(privatecoachinfo);
        int pid = privatecoachinfo.getPid();
        if (pid > 0) {
            return DataResults.success(ResultCode.SUCCESS);
        } else {
            return DataResults.success(ResultCode.FAIL);
        }
    }

    @PutMapping("update")
    public DataResults update(Privatecoachinfo privatecoachinfo) {
        privatecoachinfo.setAdmin("匿名");
        privatecoachinfoService.updateById(privatecoachinfo);
        return DataResults.success(ResultCode.SUCCESS);
    }

    /**
     * 删除 * @return
     */
    @DeleteMapping("delete/{id}")
    public DataResults delete(@PathVariable("id") Integer id) {
        Privatecoachinfo privatecoachinfo = new Privatecoachinfo(id, 1);
        privatecoachinfoService.updateById(privatecoachinfo);
        return DataResults.success(ResultCode.SUCCESS);
    }

}
