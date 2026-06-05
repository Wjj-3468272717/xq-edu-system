package com.v1.service;

import com.v1.pojo.Privatecoachinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author v1
 * @since 2026-06-05
 */
public interface PrivatecoachinfoService extends IService<Privatecoachinfo> {

    int totalCount(Map<String, Object> paramMap);

    List<Privatecoachinfo> queryPage(Map<String, Object> paramMap);

    void baoke(Privatecoachinfo privatecoachinfo);
}
