package com.v1.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.v1.pojo.Cardsrecord;
import com.v1.pojo.Member;
import com.v1.pojo.Membertype;
import com.v1.service.CardsrecordService;
import com.v1.service.MemberService;
import com.v1.service.MembertypeService;
import com.v1.utils.DataResults;
import com.v1.utils.DateTimeUtils;
import com.v1.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author v1
 * @since 2026-06-04
 */
@RestController
@RequestMapping("/cardsrecord")
public class CardsrecordController {

    @Autowired
    CardsrecordService cardsrecordService;
    @Autowired
    MembertypeService membertypeService;
    @Autowired
    MemberService memberService;

    @PostMapping("/extendCard")
    public DataResults extendCard(Cardsrecord cardsrecord) {
        try {
            cardsrecord.setDel(0);
            cardsrecord.setCreatetime(DateTimeUtils.nowTime());
            cardsrecordService.extendCard(cardsrecord);
            return DataResults.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return DataResults.success(ResultCode.FAIL);
        }
    }

    @GetMapping("queryPage")
    public Map<String, Object> queryPage(Integer pageSize, Integer pageNumber, String xdate, String ddate) {
        Map<String, Object> resultMap = new HashMap<>();
        QueryWrapper<Cardsrecord> q = new QueryWrapper<>();
        q.ge(StringUtils.isNotEmpty(xdate),"createtime", xdate);
        q.le(StringUtils.isNotEmpty(ddate),"createtime", ddate);
        q.eq("del", 0);
        IPage<Cardsrecord> page = cardsrecordService.page(new Page<Cardsrecord>(pageNumber, pageSize), q);
        List<Cardsrecord> list = page.getRecords();
        for(Cardsrecord cardsrecord : list){
            Member member = memberService.getById(cardsrecord.getMemberId());
            Membertype membertype = membertypeService.getById(cardsrecord.getTypeId());
            member.setMembertype(membertype);

            cardsrecord.setMember(member);
        }
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",list);
        return resultMap;
    }

}
