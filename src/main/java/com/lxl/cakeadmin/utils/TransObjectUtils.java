package com.lxl.cakeadmin.utils;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxl.cakeadmin.entity.CakeLabel;
import com.lxl.cakeadmin.entity.CakeProduct;
import com.lxl.cakeadmin.entity.CakeProductLabel;
import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.security.UserDetailsService;
import com.lxl.cakeadmin.service.CakeLabelService;
import com.lxl.cakeadmin.service.CakeProductLabelService;
import com.lxl.cakeadmin.vo.CakeProductVo;
import com.lxl.cakeadmin.vo.CakeUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private CakeProductLabelService cakeProductLabelService;

    @Autowired
    private CakeLabelService cakeLabelService;

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

    public CakeProductVo cakeProductTransToCakeProductVo(CakeProduct cakeProduct) {
        List<CakeProductLabel> list = cakeProductLabelService.list(new LambdaQueryWrapper<CakeProductLabel>().eq(CakeProductLabel::getCakeProductId, cakeProduct.getCakeProductId()));
        List<Long> ids = new ArrayList<>();
        list.forEach(cakeProductLabel -> ids.add(cakeProductLabel.getCakeLabelId()));
        List<CakeLabel> cakeLabels;
        if (ids.isEmpty()) {
            cakeLabels = null;
        } else {
            cakeLabels = cakeLabelService.listByIds(ids);
        }
        CakeProductVo cakeProductVo = new CakeProductVo();
        cakeProductVo.setCakeProductId(cakeProduct.getCakeProductId());
        cakeProductVo.setCakeProductName(cakeProduct.getCakeProductName());
        cakeProductVo.setCakeProductPhoto(cakeProduct.getCakeProductPhoto());
        cakeProductVo.setCakeProductPrice(cakeProduct.getCakeProductPrice());
        cakeProductVo.setCreateTime(cakeProduct.getCreateTime());
        cakeProductVo.setCakeLabelList(cakeLabels);
        return cakeProductVo;
    }


}
