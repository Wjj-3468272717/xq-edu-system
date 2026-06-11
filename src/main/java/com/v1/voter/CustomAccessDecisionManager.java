package com.v1.voter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component(value = "customAccessDecisionManager")
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        //获取当前用户认证的角色信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //循环遍历当前请求需要的角色信息，满足一个即可
        for(ConfigAttribute configAttribute : configAttributes){
            if("ROLE_null".equals(configAttribute.getAttribute())){
                //不做权限控制
                return;
            }
            //循环判断用户的角色权限是否符合当前资源请求需要用到的权限
            for(GrantedAuthority authority : authorities){
                if(authority.getAuthority().equals(configAttribute.getAttribute())){
                    //权限通过
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，无法访问");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
