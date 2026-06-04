package com.v1.service.impl;

import com.v1.pojo.Chargerecords;
import com.v1.pojo.Member;
import com.v1.mapper.MemberMapper;
import com.v1.service.ChargerecordsService;
import com.v1.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.v1.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author v1
 * @since 2026-06-03
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    MemberMapper memberMapper;
    @Override
    public int totalCount(Map<String, Object> paramMap) {
        return memberMapper.totalCount(paramMap);
    }

    @Override
    public List<Member> pageMembers(Map<String, Object> paramMap) {
        return memberMapper.pageMembers(paramMap);
    }
}
