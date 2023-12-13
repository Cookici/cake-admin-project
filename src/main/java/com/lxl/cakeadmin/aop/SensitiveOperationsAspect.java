package com.lxl.cakeadmin.aop;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxl.cakeadmin.common.Constant;
import com.lxl.cakeadmin.entity.CakeRole;
import com.lxl.cakeadmin.entity.CakeRolePermit;
import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.exception.NoPermitException;
import com.lxl.cakeadmin.exception.RoleLevelException;
import com.lxl.cakeadmin.exception.UserLevelException;
import com.lxl.cakeadmin.service.CakeRolePermitService;
import com.lxl.cakeadmin.service.CakeRoleService;
import com.lxl.cakeadmin.service.CakeUserService;
import com.lxl.cakeadmin.utils.JwtUtils;
import com.lxl.cakeadmin.vo.CakeRolePermitVo;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
public class SensitiveOperationsAspect {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CakeUserService cakeUserService;

    @Autowired
    private CakeRoleService cakeRoleService;

    @Autowired
    private CakeRolePermitService cakeRolePermitService;

    @Pointcut("@annotation(com.lxl.cakeadmin.aop.SensitiveOperations)")
    public void judgePointCut() {
    }


    @Before("judgePointCut() && @annotation(sensitiveOperations)")
    public void beforeMethod(JoinPoint point, SensitiveOperations sensitiveOperations) throws RoleLevelException, UserLevelException, NoPermitException {
        String value = sensitiveOperations.value();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader("Authorization");
        Claims claims = jwtUtils.getClaimsByToken(token);
        String subject = claims.getSubject();
        Object[] args = point.getArgs();
        if (Constant.ROLE.equals(value)) {
            for (Object arg : args) {
                if (arg instanceof CakeRole) {
                    CakeRole cakeRole = (CakeRole) arg;
                    CakeUser cakeUser = cakeUserService.selectUserByUsername(subject);
                    Integer cakeRoleId = cakeUser.getCakeRoleId();
                    CakeRole userRole = cakeRoleService.getById(cakeRoleId);
                    if (cakeRole.getCakeRoleLevel() >= userRole.getCakeRoleLevel()) {
                        throw new RoleLevelException("无权操作关于该角色");
                    }
                    log.info("CakeRole 操作: " + cakeRole);
                } else if (arg instanceof Long) {
                    Long optionRoleId = (Long) arg;
                    getOptionsRoleAndUserRole(subject, optionRoleId);
                } else if (arg instanceof List) {
                    List<Long> optionRoleIds = (List) arg;
                    for (Long optionRoleId : optionRoleIds) {
                        getOptionsRoleAndUserRole(subject, optionRoleId);
                    }
                }
            }
        } else if (Constant.USER.equals(value)) {
            for (Object arg : args) {
                if (arg instanceof CakeUser) {
                    CakeUser optionUser = (CakeUser) arg;
                    CakeUser cakeUser = cakeUserService.getById(optionUser.getCakeUserId());
                    CakeRole optionUserRole = cakeRoleService.getById(cakeUser.getCakeRoleId());
                    CakeUser executeCakeUser = cakeUserService.selectUserByUsername(subject);
                    CakeRole executeUserRole = cakeRoleService.getById(executeCakeUser.getCakeRoleId());
                    if (optionUserRole.getCakeRoleLevel() >= executeUserRole.getCakeRoleLevel()) {
                        throw new UserLevelException("无权操作该用户");
                    }
                }
            }
        } else if (Constant.PERMIT.equals(value)) {
            for (Object arg : args) {
                if (arg instanceof CakeRolePermitVo) {
                    CakeRolePermitVo cakeRolePermitVo = (CakeRolePermitVo) arg;
                    List<Long> optineCakePermitIdList = cakeRolePermitVo.getCakePermitIdList();


                    CakeUser cakeUser = cakeUserService.selectUserByUsername(subject);
                    Integer executeUserRoleId = cakeUser.getCakeRoleId();

                    List<CakeRolePermit> cakeRolePermitList = cakeRolePermitService.list(new LambdaQueryWrapper<CakeRolePermit>().eq(CakeRolePermit::getCakeRoleId, executeUserRoleId));
                    List<Long> cakePermitIdList = new ArrayList<>();
                    cakeRolePermitList.forEach(cakeRolePermit -> cakePermitIdList.add(cakeRolePermit.getCakePermitId()));

                    if (!new HashSet<>(cakePermitIdList).containsAll(optineCakePermitIdList)) {
                        throw new NoPermitException("非法操作权限");
                    }
                }
            }
        }

        log.info("执行方法 ====> {}", point.getSignature());
        log.info("用户名称 ===> {}", subject);

    }

    private void getOptionsRoleAndUserRole(String subject, Long optionRoleId) throws RoleLevelException {
        CakeRole optionRole = cakeRoleService.getById(optionRoleId);
        CakeUser userRole = cakeUserService.selectUserByUsername(subject);
        CakeRole cakeRole = cakeRoleService.getById(userRole.getCakeRoleId());
        if (optionRole.getCakeRoleLevel() >= cakeRole.getCakeRoleLevel()) {
            throw new RoleLevelException("无权操作" + optionRole.getCakeRoleDescription() + "该角色");
        }
        log.info("CakeRole 操作: " + cakeRole);
    }

}
