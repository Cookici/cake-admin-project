package com.lxl.cakeadmin.security;

import com.alibaba.fastjson2.JSON;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.result.ResultCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.security
 * @ClassName: JwtAccessDeniedHandler
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/6 9:07
 */

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        Result<String> result = Result.fail("无权进行该操纵").code(ResultCodeEnum.NO_RIGHT.getCode()).message(ResultCodeEnum.NO_RIGHT.getMessage());
        outputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}

