package com.v1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.v1.mapper.AdminRoleMenusMapper;
import com.v1.pojo.AdminRoleMenus;
import com.v1.pojo.AdminUserRole;
import com.v1.pojo.Adminrole;
import com.v1.mapper.AdminroleMapper;
import com.v1.service.AdminRoleMenusService;
import com.v1.service.AdminUserRoleService;
import com.v1.service.AdminmenusService;
import com.v1.service.AdminroleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
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
public class AdminroleServiceImpl extends ServiceImpl<AdminroleMapper, Adminrole> implements AdminroleService {

    @Autowired
    AdminroleMapper adminroleMapper;
    @Autowired
    AdminRoleMenusService adminRoleMenusService;
    @Autowired
    private AdminUserRoleService adminUserRoleService;
    @Autowired
    private AdminroleService adminroleService;


    @Transactional
    @Override
    public void addRole(Adminrole adminrole, String menusIds) {
        //新增角色信息
        adminroleMapper.insert(adminrole);
        //添加关联
        String[] split = menusIds.split(",");
        List<AdminRoleMenus> list = new ArrayList<>();
        for(String menusId : split){
            AdminRoleMenus adminRoleMenus = new AdminRoleMenus(null, adminrole.getId(), Integer.valueOf(menusId));
            list.add(adminRoleMenus);
        }
        adminRoleMenusService.saveBatch(list);
    }

    @Override
    @Transactional
    public void updateRole(Adminrole adminrole, String menusIds) {
        //修改角色信息
        adminroleMapper.updateById(adminrole);
        //更新关联表信息
        adminRoleMenusService.remove(new QueryWrapper<AdminRoleMenus>().eq("roleId",adminrole.getId()));
        List<AdminRoleMenus> list = new ArrayList<>();
        String[] split = menusIds.split(",");
        for(String menusId : split){
            AdminRoleMenus adminRoleMenus = new AdminRoleMenus(null,adminrole.getId(),Integer.valueOf(menusId));
            list.add(adminRoleMenus);
        }
        adminRoleMenusService.saveBatch(list);
    }

    @Override
    @Transactional
    public boolean delRole(Adminrole adminrole) {
        //逻辑删除角色表的数据
        int updated = adminroleMapper.updateById(adminrole);
        //物理删除role，menus 关联表
        boolean removed = adminRoleMenusService.remove(new QueryWrapper<AdminRoleMenus>().eq("roleId", adminrole.getId()));
        return removed && updated > 0;
    }

}
