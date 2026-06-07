package com.v1.service.impl;

import com.v1.pojo.Goodinfo;
import com.v1.mapper.GoodinfoMapper;
import com.v1.pojo.Goods;
import com.v1.pojo.Member;
import com.v1.service.GoodinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.v1.service.GoodsService;
import com.v1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author v1
 * @since 2026-06-06
 */
@Service
public class GoodinfoServiceImpl extends ServiceImpl<GoodinfoMapper, Goodinfo> implements GoodinfoService {

    @Autowired
    GoodsService goodsService;
    @Autowired
    MemberService memberService;
    @Autowired
    GoodinfoMapper goodinfoMapper;

    @Override
    @Transactional
    public Boolean sell(Goodinfo goodinfo) {
        try {
            //1. 扣库存
            Goods goods = goodsService.getById(goodinfo.getGoodsid());
            goods.setInventory(goods.getInventory() - goodinfo.getCount());
            goodsService.updateById(goods);
            //2. 捡余额
            Member member = memberService.getById(goodinfo.getMemberid());
            member.setMemberbalance(member.getMemberbalance() - goodinfo.getPrice());
            memberService.updateById(member);
            //3. 增记录
            goodinfoMapper.insert(goodinfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
