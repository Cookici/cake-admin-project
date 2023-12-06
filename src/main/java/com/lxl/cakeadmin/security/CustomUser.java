package com.lxl.cakeadmin.security;

import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.service.CakeUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.security
 * @ClassName: CustomUser
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/4 17:39
 */

public class CustomUser extends User implements Serializable {

    private CakeUser cakeUser;

    public CustomUser(CakeUser cakeUser, Collection<? extends GrantedAuthority> authorities) {
        super(cakeUser.getCakeUserUsername(), cakeUser.getCakeUserPassword(), authorities);
        this.cakeUser = cakeUser;
    }

    public CakeUser getCakeUser() {
        return cakeUser;
    }

    public void setCakeUser(CakeUser cakeUser) {
        this.cakeUser = cakeUser;
    }
}
