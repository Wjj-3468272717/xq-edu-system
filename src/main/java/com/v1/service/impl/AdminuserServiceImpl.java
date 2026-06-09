package com.v1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.v1.pojo.Adminuser;
import com.v1.mapper.AdminuserMapper;
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
import org.springframework.stereotype.Service;

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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("页面上输入的用户名： "+username);
        Adminuser adminuser = adminuserMapper.selectOne(new QueryWrapper<Adminuser>().eq("adminName", username));
        if(adminuser == null){
            throw new UsernameNotFoundException("用户信息不存在");
        }else{
            List<GrantedAuthority> list = new ArrayList<>();
            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(adminuser.getAdminName(),adminuser.getAdminPassword().trim(),list);
        }
    }

}
