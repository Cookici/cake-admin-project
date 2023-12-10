package com.lxl.cakeadmin.security;


import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.service.CakeUserService;
import com.lxl.cakeadmin.utils.JwtUtils;
import com.lxl.cakeadmin.utils.MatchUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


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

    @Autowired
    private CakeUserService cakeUserService;

    @Autowired
    private UserDetailsService userDetailsService;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("JwtAuthenticationFilter ===> 开始过滤");

        String jwt = request.getHeader(jwtUtils.getHeader());
        String requestUri = request.getRequestURI();
        if (MatchUtils.isMatch(requestUri, WhiteList.URL_WHITELIST)) {
            log.info("白名单uri ===> {}", requestUri);
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

        CakeUser cakeUser = cakeUserService.selectUserByUsername(username);
        List<GrantedAuthority> userAuthority = userDetailsService.getUserAuthority(cakeUser);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, null, userAuthority);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }




}
