package com.v1.service.impl;

import com.v1.pojo.Member;
import com.v1.mapper.MemberMapper;
import com.v1.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
