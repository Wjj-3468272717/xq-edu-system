package com.v1.handler;

import com.google.gson.Gson;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异步认证失败
 */
@Component(value = "authenticationFailureHandler")
@Slf4j
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        log.info("登录失败:"+exception.getMessage()+"登录异常的类型是："+exception);
        DataResults<String> results = DataResults.success(ResultCode.LOGIN_FAIL);
        response.getWriter().write(new Gson().toJson(results));
    }
}
