package com.v1.service.impl;

import com.v1.pojo.Cardsrecord;
import com.v1.mapper.CardsrecordMapper;
import com.v1.pojo.Member;
import com.v1.pojo.Membertype;
import com.v1.service.CardsrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.v1.service.MemberService;
import com.v1.service.MembertypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author v1
 * @since 2026-06-04
 */
@Service
public class CardsrecordServiceImpl extends ServiceImpl<CardsrecordMapper, Cardsrecord> implements CardsrecordService {

    @Autowired
    MemberService memberService;
    @Autowired
    MembertypeService membertypeService;
    @Autowired
    private CardsrecordService cardsrecordService;

    @Override
    @Transactional
    public void extendCard(Cardsrecord cardsrecord) {
        //更新到期时间和余额信息
        Member member = memberService.getById(cardsrecord.getMemberId());
        Integer typeId = cardsrecord.getTypeId();
//        member.setMembertype(membertypeService.getById(typeId));
        member.setMemberTypes(typeId);
        member.setMemberStatic(1);
        member.setMemberxufei(cardsrecord.getDaoqi());
        member.setMemberbalance(member.getMemberbalance() - cardsrecord.getMoney());
        memberService.updateById(member);
        //添加续卡记录
        cardsrecordService.save(cardsrecord);
    }
}
