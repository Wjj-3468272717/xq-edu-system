package com.v1.mapper;

import com.v1.pojo.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author v1
 * @since 2026-06-03
 */
public interface MemberMapper extends BaseMapper<Member> {

    List<Member> pageMembers(Map<String, Object> paramMap);

    int totalCount(Map<String, Object> paramMap);
}
