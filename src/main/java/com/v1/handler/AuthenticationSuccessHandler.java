package com.v1.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.v1.pojo.Adminuser;
import com.v1.service.AdminuserService;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异步认证成功
 */
@Component(value = "authenticationSuccessHandle")
@Slf4j
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    AdminuserService adminuserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("登录成功的用户是:"+userDetails.getUsername());
        //存储用户认证成功的用户东西
        Adminuser adminuser = adminuserService.getOne(new QueryWrapper<Adminuser>().eq("adminName", userDetails.getUsername()));
        request.getSession().setAttribute("loginUser",adminuser);
        DataResults<String> results = DataResults.success(ResultCode.SUCCESS);
        response.getWriter().write(new Gson().toJson(results));
    }
}
