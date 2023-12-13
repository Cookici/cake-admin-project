package com.lxl.cakeadmin.service;

import com.lxl.cakeadmin.entity.CakeRolePermit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.cakeadmin.vo.CakeRolePermitVo;

public interface CakeRolePermitService extends IService<CakeRolePermit>{


    String deleteList(CakeRolePermitVo cakeRolePermitVo);
}
