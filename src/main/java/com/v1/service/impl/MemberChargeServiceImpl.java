package com.v1.service.impl;

import com.v1.mapper.MemberMapper;
import com.v1.pojo.Chargerecords;
import com.v1.pojo.Member;
import com.v1.service.ChargerecordsService;
import com.v1.service.MemberChargeService;
import com.v1.service.MemberService;
import com.v1.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberChargeServiceImpl implements MemberChargeService {

    @Autowired
    private MemberService memberService;
    @Autowired
    private ChargerecordsService chargerecordsService;

    @Transactional
    @Override
    public void charge(Chargerecords chargerecords) {
        //更新余额信息
        Member member = memberService.getById(chargerecords.getMemberId());
        member.setMemberbalance(member.getMemberbalance() + chargerecords.getMoney());
        memberService.updateById(member);
        //新增充值记录
        chargerecords.setChargetime(DateTimeUtils.nowTime());
        chargerecords.setDel(0);
        chargerecordsService.save(chargerecords);
    }
}
