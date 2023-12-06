package com.lxl.cakeadmin.security;

import com.lxl.cakeadmin.entity.CakeUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.security
 * @ClassName: UserDetailsService
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/4 17:40
 */

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<GrantedAuthority> getUserAuthority(CakeUser cakeUser);
}
