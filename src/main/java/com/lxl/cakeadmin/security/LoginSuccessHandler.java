package com.lxl.cakeadmin.security;


import com.alibaba.fastjson2.JSON;
import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakeLabelService;
import com.lxl.cakeadmin.service.CakeUserService;
import com.lxl.cakeadmin.utils.JwtUtils;
import com.lxl.cakeadmin.vo.CakeUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private CakeUserService cakeUserService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        // 生成jwt返回
        String jwt = jwtUtils.generateToken(authentication.getName());
        response.setHeader(jwtUtils.getHeader(), jwt);

        CakeUser cakeUser = cakeUserService.selectUserByUsername(authentication.getName());
        CakeUserVo cakeUserVo = setCakeUserVo(cakeUser);

        Result<CakeUserVo> result = Result.ok(cakeUserVo).message("登录成功");
        outputStream.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }

    private CakeUserVo setCakeUserVo(CakeUser cakeUser) {
        CakeUserVo cakeUserVo = new CakeUserVo();
        cakeUserVo.setCakeUserId(cakeUser.getCakeUserId());
        cakeUserVo.setCakeUserNickName(cakeUser.getCakeUserNickName());
        cakeUserVo.setCakeUserUsername(cakeUser.getCakeUserUsername());
        cakeUserVo.setCakeUserIp(cakeUser.getCakeUserIp());
        cakeUserVo.setCakeUserLevel(cakeUser.getCakeUserLevel());
        cakeUserVo.setCakeUserPhone(cakeUser.getCakeUserPhone());
        cakeUserVo.setCakeUserAuthority(userDetailsService.getUserAuthority(cakeUser));
        return cakeUserVo;
    }

}

