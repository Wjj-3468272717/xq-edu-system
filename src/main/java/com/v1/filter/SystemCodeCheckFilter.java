package com.v1.filter;

import com.alibaba.druid.util.StringUtils;
import com.google.gson.Gson;
import com.v1.utils.DataResults;
import com.v1.utils.ResultCode;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component(value = "systemCodeCheckFilter")
public class SystemCodeCheckFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals("/login",request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(),"post")){
            String systemCode = (String) request.getSession().getAttribute("systemCode");
            String sysCode = request.getParameter("syscode");
            if(systemCode.equalsIgnoreCase(sysCode)){
                filterChain.doFilter(request,response);
            }else{
                response.setContentType("application/json;charset=utf-8");
                DataResults results = DataResults.success(ResultCode.CODE_FAIL);
                response.getWriter().write(new Gson().toJson(results));
            }
        }else{
            filterChain.doFilter(request,response);
        }
    }
}
