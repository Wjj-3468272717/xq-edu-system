package com.v1.service;

import com.v1.mapper.AdminuserMapper;
import com.v1.pojo.Adminuser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author v1
 * @since 2026-06-09
 */

public interface AdminuserService extends IService<Adminuser>{


    void add(Adminuser adminuser, String roleIds);

    void updateAdminUser(Adminuser adminuser, String roleIds);
}
