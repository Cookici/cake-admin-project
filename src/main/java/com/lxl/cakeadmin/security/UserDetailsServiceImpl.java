package com.lxl.cakeadmin.security;

import com.alibaba.fastjson2.JSON;
import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.service.CakeUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.security
 * @ClassName: UserDetailsServiceImpl
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/4 17:40
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CakeUserService cakeUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CakeUser cakeUser = cakeUserService.selectUserByUsername(username);
        if (cakeUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        String cakeUserAuthority = cakeUser.getCakeUserAuthority();
        List<GrantedAuthority> list = AuthorityUtils.commaSeparatedStringToAuthorityList(cakeUserAuthority);
        return new CustomUser(cakeUser, list);
    }
}
