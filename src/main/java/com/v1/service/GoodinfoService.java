package com.v1.service;

import com.v1.pojo.Goodinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author v1
 * @since 2026-06-06
 */
public interface GoodinfoService extends IService<Goodinfo> {

    Boolean sell(Goodinfo goodinfo);
}
