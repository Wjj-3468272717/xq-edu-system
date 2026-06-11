package com.v1.filter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.v1.pojo.Adminmenus;
import com.v1.pojo.Adminrole;
import com.v1.service.AdminmenusService;
import com.v1.service.AdminroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 动态授权过滤器
 */
@Component(value = "customerFilterInvocationSecurityMetaDataSource")
public class CustomerFilterInvocationSecurityMetaDataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    AdminmenusService adminmenusService;
    @Autowired
    AdminroleService adminroleService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    //动态获取某个资源需要用到哪些权限
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //Object 封装了 request  response
        FilterInvocation request = (FilterInvocation) object;
        //获取用户访问资源地址
        String url = request.getRequestUrl();
        //进行数据库查询操作，获取对应资源与角色的信息
        List<Adminmenus> list = adminmenusService.list(new QueryWrapper<Adminmenus>().eq("type", 1).eq("del", 0));
        //遍历所有的资源，查看是否和当前的url进行匹配
        for(Adminmenus adminmenus : list){
            if(adminmenus!=null && antPathMatcher.match(adminmenus.getUrl(),url)){
                //匹配url通过
                String remark = adminmenus.getRemark();
                String[] roles = remark.split(",");
                String[] roleStr = new String[roles.length];
                for(int i = 0; i < roles.length; i++){
                    String roleId = roles[i];
                    Adminrole adminrole = adminroleService.getById(Integer.valueOf(roleId));
                    roleStr[i] = "ROLE_"+adminrole.getRoleName();
                }
                return SecurityConfig.createList(roleStr);
            }
        }
        //没有匹配上，返回ROLE_null,不做任何权限处理
        return SecurityConfig.createList("ROLE_null");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return Collections.emptyList();
    }

    //进行类型经验支持
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
