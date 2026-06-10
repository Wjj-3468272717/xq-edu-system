package com.v1.service.impl;

import com.v1.pojo.Adminmenus;
import com.v1.mapper.AdminmenusMapper;
import com.v1.service.AdminmenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author v1
 * @since 2026-06-10
 */
@Service
public class AdminmenusServiceImpl extends ServiceImpl<AdminmenusMapper, Adminmenus> implements AdminmenusService {

    @Autowired
    AdminmenusMapper adminmenusMapper;

    @Override
    public List<Adminmenus> listMenusByAdminId(Integer adminId) {
        return adminmenusMapper.listMenusByAdminId(adminId);
    }
}
