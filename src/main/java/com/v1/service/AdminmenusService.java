package com.v1.service;

import com.v1.pojo.Adminmenus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author v1
 * @since 2026-06-10
 */
public interface AdminmenusService extends IService<Adminmenus> {

    List<Adminmenus> listMenusByAdminId(Integer adminId);
}
