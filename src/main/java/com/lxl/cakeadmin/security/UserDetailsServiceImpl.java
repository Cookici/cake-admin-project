package com.lxl.cakeadmin.security;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxl.cakeadmin.common.Constant;
import com.lxl.cakeadmin.entity.CakePermit;
import com.lxl.cakeadmin.entity.CakeRolePermit;
import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.mapper.CakeRolePermitMapper;
import com.lxl.cakeadmin.service.CakePermitService;
import com.lxl.cakeadmin.service.CakeRolePermitService;
import com.lxl.cakeadmin.service.CakeUserService;
import com.lxl.cakeadmin.utils.MatchUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.security
 * @ClassName: UserDetailsServiceImpl
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/4 17:40
 */
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CakeUserService cakeUserService;

    @Autowired
    private CakeRolePermitService cakeRolePermitService;

    @Autowired
    private CakePermitService cakePermitService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CakeUser cakeUser = cakeUserService.selectUserByUsername(username);
        log.info("当前用户尝试登录 ====> {}", cakeUser);
        if (cakeUser == null) {
            log.error("用户名或密码错误");
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        if (!MatchUtils.isMatch(cakeUser.getCakeRoleId(), WhiteList.ROLE_WHITELIST)) {
            log.error("当前账号无法登录后台");
            throw new AccessDeniedException("当前账号无法登录后台");
        }

        List<GrantedAuthority> userAuthority = getUserAuthority(cakeUser);

        return new CustomUser(cakeUser, userAuthority);
    }


    @Override
    public List<GrantedAuthority> getUserAuthority(CakeUser cakeUser) {
        List<CakeRolePermit> cakeRolePermitList = cakeRolePermitService.list(new LambdaQueryWrapper<CakeRolePermit>().eq(CakeRolePermit::getCakeRoleId, cakeUser.getCakeRoleId()));
        List<Long> permitIds = new ArrayList<>();
        cakeRolePermitList.forEach(cakeRolePermit -> permitIds.add(cakeRolePermit.getCakePermitId()));
        List<CakePermit> cakePermits = cakePermitService.listByIds(permitIds);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < cakePermits.size(); i++) {
            if (i != cakePermits.size() - 1) {
                stringBuilder.append(cakePermits.get(i).getCakePermitName()).append(",");
            } else {
                stringBuilder.append(cakePermits.get(i).getCakePermitName());
            }

        }
        log.info("authorities ===> {}", stringBuilder);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuilder.toString());
    }

}
