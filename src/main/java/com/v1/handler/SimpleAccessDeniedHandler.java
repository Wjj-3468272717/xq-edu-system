package com.v1.handler;

import com.google.gson.Gson;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问权限不足的处理器
 */
@Component(value = "simpleAccessDeniedHandler")
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        response.setContentType("text/html;charset=utf-8");
        if(requestURI != null && requestURI.endsWith(".html")){//同步请求
            response.sendRedirect("/403.html");
        }else{//异步请求
            DataResults.success(ResultCode.NO_RIGHTS);
            response.getWriter().write(new Gson().toJson(response));
        }
    }
}
