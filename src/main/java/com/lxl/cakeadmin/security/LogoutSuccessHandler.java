package com.lxl.cakeadmin.security;


import com.alibaba.fastjson2.JSON;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ProjectName: shixun-blog
 * @Package: com.zxw.shixunblog.security
 * @ClassName: logoutSuccessHandler
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/4 20:36
 */
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        httpServletResponse.setHeader(jwtUtils.getHeader(), "");

        Result<String> result = Result.ok("登出成功");

        outputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
