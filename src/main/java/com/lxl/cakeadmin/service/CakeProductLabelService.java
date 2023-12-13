package com.lxl.cakeadmin.service;

import com.lxl.cakeadmin.entity.CakeProductLabel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lxl.cakeadmin.vo.CakeProductLabelVo;

public interface CakeProductLabelService extends IService<CakeProductLabel>{


    String deleteList(CakeProductLabelVo cakeProductLabelVo);

}
