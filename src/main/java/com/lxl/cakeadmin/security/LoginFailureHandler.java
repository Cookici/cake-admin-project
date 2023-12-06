package com.lxl.cakeadmin.security;


import com.alibaba.fastjson2.JSON;
import com.lxl.cakeadmin.result.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ProjectName: shixun-blog
 * @Package: com.zxw.shixunblog.security
 * @ClassName: LogiFailureHandler
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/4 20:16
 */

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        Result<Object> result = Result.fail().message("Bad credentials".equals(exception.getMessage()) ? "用户名或密码不正确" : exception.getMessage());
        outputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}

