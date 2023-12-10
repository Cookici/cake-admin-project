package com.lxl.cakeadmin.utils;


import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.security.UserDetailsService;
import com.lxl.cakeadmin.vo.CakeUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.utils
 * @ClassName: TransObjectUtils
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/7 13:47
 */
@Component
public class TransObjectUtils {

    @Autowired
    private UserDetailsService userDetailsService;

    public CakeUserVo cakeUserTransToCakeUserVo(CakeUser cakeUser) {
        CakeUserVo cakeUserVo = new CakeUserVo();
        cakeUserVo.setCakeUserId(cakeUser.getCakeUserId());
        cakeUserVo.setCakeUserNickName(cakeUser.getCakeUserNickName());
        cakeUserVo.setCakeUserUsername(cakeUser.getCakeUserUsername());
        cakeUserVo.setCakeUserIp(cakeUser.getCakeUserIp());
        cakeUserVo.setCakeUserLevel(cakeUser.getCakeUserLevel());
        cakeUserVo.setCakeUserPhone(cakeUser.getCakeUserPhone());
        cakeUserVo.setCakeUserAvatar(cakeUser.getCakeUserAvatar());
        cakeUserVo.setCakeUserAuthority(userDetailsService.getUserAuthority(cakeUser));
        cakeUserVo.setCakeUserStatus(cakeUser.getCakeUserStatus());
        cakeUserVo.setCreateTime(cakeUser.getCreateTime());
        return cakeUserVo;
    }


}
