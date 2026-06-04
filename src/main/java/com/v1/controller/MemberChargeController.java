package com.v1.controller;

import com.v1.pojo.Chargerecords;
import com.v1.pojo.Member;
import com.v1.service.ChargerecordsService;
import com.v1.service.MemberChargeService;
import com.v1.service.MemberService;
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

@RestController
@RequestMapping("member-charge")
public class MemberChargeController {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberChargeService memberChargeService;

    @GetMapping("queryPageCharge")
    public Map<String,Object> queryPageCharge(String hyname, Integer ktype, Integer pageNumber, Integer pageSize){
        Map<String, Object> resultMap = new HashMap<>();
        Map<String,Object> paramMap = new HashMap<>();

        paramMap.put("hyname",hyname);
        paramMap.put("ktype",ktype);
        if(pageSize == null){
            pageSize = 10;
        }
        if(pageNumber == null){
            pageNumber = 1;
        }
        paramMap.put("pageStart",(pageNumber - 1) * pageSize);
        paramMap.put("pageSize",pageSize);

        int totalCount = memberService.totalCount(paramMap);
        List<Member> list = memberService.pageMembers(paramMap);
        resultMap.put("total",totalCount);
        resultMap.put("rows",list);
        return resultMap;
    }

    /**
     * 1. 保存充值记录
     * 2. 修改学员余额
     * @param chargerecords
     * @return
     */

    @PostMapping("/charge")
    public DataResults charge(Chargerecords chargerecords){
        try {
            chargerecords.setDel(0);
            chargerecords.setChargetime(DateTimeUtils.nowTime());
           memberChargeService.charge(chargerecords);
            return DataResults.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            return DataResults.success(ResultCode.FAIL);
        }
    }

}
