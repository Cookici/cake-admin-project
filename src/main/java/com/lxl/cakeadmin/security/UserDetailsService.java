package com.lxl.cakeadmin.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
}
