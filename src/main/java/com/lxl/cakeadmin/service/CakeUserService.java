package com.lxl.cakeadmin.service;

import com.lxl.cakeadmin.entity.CakeUser;
import com.baomidou.mybatisplus.extension.service.IService;
public interface CakeUserService extends IService<CakeUser>{

    /**
     * 通过username获取CakeUser
     * @param username 用户账号
     * @return 用户对象
     */
    CakeUser selectUserByUsername(String username);

}
