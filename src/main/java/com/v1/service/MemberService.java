package com.v1.service;

import com.v1.pojo.Member;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author v1
 * @since 2026-06-03
 */
public interface MemberService extends IService<Member> {

    int totalCount(Map<String, Object> paramMap);

    List<Member> pageMembers(Map<String, Object> paramMap);
}
