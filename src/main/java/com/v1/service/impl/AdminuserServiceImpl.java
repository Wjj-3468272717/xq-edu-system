package com.v1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.v1.pojo.AdminUserRole;
import com.v1.pojo.Adminrole;
import com.v1.pojo.Adminuser;
import com.v1.mapper.AdminuserMapper;
import com.v1.service.AdminUserRoleService;
import com.v1.service.AdminroleService;
import com.v1.service.AdminuserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author v1
 * @since 2026-06-09
 */
@Service(value = "adminuserService")
@Slf4j
public class AdminuserServiceImpl extends ServiceImpl<AdminuserMapper, Adminuser> implements AdminuserService, UserDetailsService {

    @Autowired
    AdminuserMapper adminuserMapper;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminroleService adminroleService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("页面上输入的用户名： "+username);
        Adminuser adminuser = adminuserMapper.selectOne(new QueryWrapper<Adminuser>().eq("adminName", username));
        if(adminuser == null){
            throw new UsernameNotFoundException("用户信息不存在");
        }else{
//            List<GrantedAuthority> list = new ArrayList<>();
//            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            //动态获取用户的角色信息
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            //获取用户的编号
            Integer adminId = adminuser.getAdminId();
            List<AdminUserRole> userRoles = adminUserRoleService.list(new QueryWrapper<AdminUserRole>().eq("adminId", adminId));
            for(AdminUserRole userRole : userRoles){
                Adminrole adminrole = adminroleService.getById(userRole.getRoleId());
                String roleName = adminrole.getRoleName();
                authorities.add(new SimpleGrantedAuthority("ROLE_"+roleName));
            }
            return new User(adminuser.getAdminName(),adminuser.getAdminPassword().trim(),authorities);
        }
    }

    @Override
    @Transactional
    public void add(Adminuser adminuser, String roleIds) {
        String encode = new BCryptPasswordEncoder().encode(adminuser.getAdminPassword());
        adminuser.setAdminPassword(encode);
        //新增用户表
        adminuserMapper.insert(adminuser);
        //新增关系表
        String[] split = roleIds.split(",");
        List<AdminUserRole> adminUserRoleList = new ArrayList<>();
        for(String roleId : split){
            AdminUserRole adminUserRole = new AdminUserRole(null,adminuser.getAdminId(),Integer.valueOf(roleId));
            adminUserRoleList.add(adminUserRole);
        }
        adminUserRoleService.saveBatch(adminUserRoleList);
    }

    @Override
    @Transactional
    public void updateAdminUser(Adminuser adminuser, String roleIds) {
        //更新用户表
        adminuserMapper.updateById(adminuser);
        // 更新关联关系表
        adminUserRoleService.remove(new QueryWrapper<AdminUserRole>().eq("adminId",adminuser.getAdminId()));
        String[] split = roleIds.split(",");
        List<AdminUserRole> list = new ArrayList<>();
        for(String roleId : split){
            AdminUserRole adminUserRole = new AdminUserRole(null, adminuser.getAdminId(), Integer.valueOf(roleId));
            list.add(adminUserRole);
        }
        adminUserRoleService.saveBatch(list);
    }
}
