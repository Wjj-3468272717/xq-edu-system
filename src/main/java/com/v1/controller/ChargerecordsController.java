package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.v1.pojo.Chargerecords;
import com.v1.pojo.Member;
import com.v1.pojo.Membertype;
import com.v1.service.ChargerecordsService;
import com.v1.service.MemberService;
import com.v1.service.MembertypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-04
 */
@RestController
@RequestMapping("/chargerecords")
public class ChargerecordsController {

    @Autowired
    ChargerecordsService chargerecordsService;
    @Autowired
    MemberService memberService;
    @Autowired
    MembertypeService membertypeService;

    @GetMapping("/queryPage")
    public Map<String,Object> querypage(Integer pageNumber, Integer pageSize, String xdate, String ddate){
        Map<String,Object> resultMap = new HashMap<>();

        QueryWrapper<Chargerecords> q = new QueryWrapper<>();
        q.ge(StringUtils.isNotEmpty(xdate),"chargetime",xdate);
        q.le(StringUtils.isNotEmpty(ddate),"chargetime",ddate);
        q.eq("del",0);

        IPage<Chargerecords> page = chargerecordsService.page(new Page<Chargerecords>(pageNumber,pageSize),q);
        List<Chargerecords> list = page.getRecords();
        for(Chargerecords record : list){
            Member member = memberService.getById(record.getMemberId());
            Membertype membertype = membertypeService.getById(member.getMemberTypes());
            member.setMembertype(membertype);
            record.setMember(member);
        }
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",list);
        return resultMap;
    }
}
