package com.lxl.cakeadmin.security;


import com.lxl.cakeadmin.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @ProjectName: shixun-blog
 * @Package: com.zxw.shixunblog.security
 * @ClassName: JwtAuthenticationFilter
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/4 20:26
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtUtils jwtUtils;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader(jwtUtils.getHeader());
        boolean judge = Arrays.asList(WhiteList.URL_WHITELIST).contains(request.getRequestURI());
        log.info("request.getRequestURI() ==> {}", request.getRequestURI());
        if (judge) {
            log.info("白名单url");
            chain.doFilter(request, response);
            return;
        }

        Claims claim = jwtUtils.getClaimsByToken(jwt);
        if (claim == null) {
            throw new JwtException("token异常！");
        }
        if (jwtUtils.isTokenExpired(claim)) {
            throw new JwtException("token已过期");
        }
        String username = claim.getSubject();
        log.info("用户-{}，正在登陆！", username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, null, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }
}
