package com.lxl.cakeadmin.security;


import com.alibaba.fastjson2.JSON;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ProjectName: shixun-blog
 * @Package: com.zxw.shixunblog.security
 * @ClassName: LoginSucessHandler
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/4 20:20
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();

        // 生成jwt返回
        String jwt = jwtUtils.generateToken(authentication.getName());
        response.setHeader(jwtUtils.getHeader(), jwt);
        Result<String> result = Result.ok("登录成功");
        outputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}

