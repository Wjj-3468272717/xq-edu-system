package com.v1.controller;

import com.v1.pojo.Member;
import com.v1.service.MemberService;
import com.v1.utils.DataResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/memberExtendCard")
public class MemberExtendCardController {

    @Autowired
    MemberService memberService;

    @GetMapping("/queryPage")
    public Map<String,Object> queryPage(Integer pageSize, Integer pageNumber, String hyname, Integer ktype){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("hyname",hyname);
        paramMap.put("ktype",ktype);
        paramMap.put("pageStart",(pageNumber - 1) * pageSize);
        paramMap.put("pageSize",pageSize);

        int totalCount = memberService.totalCount(paramMap);
        List<Member> list = memberService.pageMembers(paramMap);
        resultMap.put("total",totalCount);
        resultMap.put("rows",list);
        return resultMap;
    }


}
