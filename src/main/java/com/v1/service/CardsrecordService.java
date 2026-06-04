package com.v1.service;

import com.v1.pojo.Cardsrecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author v1
 * @since 2026-06-04
 */
public interface CardsrecordService extends IService<Cardsrecord> {

    void extendCard(Cardsrecord cardsrecord);
}
