package com.lxl.cakeadmin.aop;

import com.lxl.cakeadmin.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.aop
 * @ClassName: OwnAspect
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/6 9:11
 */

@Aspect
@Component
@Slf4j
public class OwnAspect {

    @Autowired
    private JwtUtils jwtUtils;

    @Pointcut("@annotation(com.lxl.cakeadmin.aop.Own)")
    public void judgePointCut() {
    }


    @Before("judgePointCut() && @annotation(own)")
    public void beforeMethod(JoinPoint point, Own own) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String username = request.getParameter("username");
        String token = request.getHeader("Authorization");
        Claims claims = jwtUtils.getClaimsByToken(token);
        String subject = claims.getSubject();
        log.info("subject and username ====> {} , {}", subject, username);

        if (!Objects.equals(subject, username)) {
            throw new AccessDeniedException("权限不足");
        }
        point.getSignature();
        log.info("敏感操作执行通过 ====> {},{}", username, point.getSignature());
    }

}
