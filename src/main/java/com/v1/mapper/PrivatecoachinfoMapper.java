package com.v1.mapper;

import com.v1.pojo.Privatecoachinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author v1
 * @since 2026-06-05
 */
public interface PrivatecoachinfoMapper extends BaseMapper<Privatecoachinfo> {

    int totalCount(Map<String, Object> paramMap);

    List<Privatecoachinfo> pages(Map<String, Object> paramMap);
}
