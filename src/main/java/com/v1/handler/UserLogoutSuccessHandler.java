package com.v1.handler;

import com.google.gson.Gson;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "userLogoutSuccessHandler")
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    //注销成功
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        DataResults results = DataResults.success(ResultCode.LOGOUT_SUCCESS);
        response.getWriter().write(new Gson().toJson(results));
    }
}
