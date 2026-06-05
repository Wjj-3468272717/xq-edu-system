package com.v1.service.impl;

import com.v1.pojo.Member;
import com.v1.pojo.Privatecoachinfo;
import com.v1.mapper.PrivatecoachinfoMapper;
import com.v1.service.MemberService;
import com.v1.service.PrivatecoachinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author v1
 * @since 2026-06-05
 */
@Service
public class PrivatecoachinfoServiceImpl extends ServiceImpl<PrivatecoachinfoMapper, Privatecoachinfo> implements PrivatecoachinfoService {

    @Autowired
    PrivatecoachinfoMapper privatecoachinfoMapper;
    @Autowired
    MemberService memberService;

    @Override
    public int totalCount(Map<String, Object> paramMap) {
        return privatecoachinfoMapper.totalCount(paramMap);
    }

    @Override
    public List<Privatecoachinfo> queryPage(Map<String, Object> paramMap) {
        return privatecoachinfoMapper.pages(paramMap);
    }

    @Transactional
    @Override
    public void baoke(Privatecoachinfo privatecoachinfo){
        //1.更新会员的余额
        Member member = memberService.getById(privatecoachinfo.getMemberid());
        member.setMemberbalance(member.getMemberbalance() - privatecoachinfo.getCountprice());
        memberService.updateById(member);
        //2.新增报课记录
        privatecoachinfoMapper.insert(privatecoachinfo);
    }
}
