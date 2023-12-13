package com.lxl.cakeadmin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxl.cakeadmin.entity.CakeProductLabel;
import com.lxl.cakeadmin.vo.CakeProductLabelVo;
import com.lxl.cakeadmin.vo.CakeRolePermitVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.cakeadmin.mapper.CakeRolePermitMapper;
import com.lxl.cakeadmin.entity.CakeRolePermit;
import com.lxl.cakeadmin.service.CakeRolePermitService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CakeRolePermitServiceImpl extends ServiceImpl<CakeRolePermitMapper, CakeRolePermit> implements CakeRolePermitService{

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteList(CakeRolePermitVo cakeRolePermitVo) {


        if (cakeRolePermitVo.getCakePermitIdList().isEmpty()) {
            return "未包含已有权限";
        }

        try {
            Integer cakeRoleId = cakeRolePermitVo.getCakeRoleId();
            LambdaQueryWrapper<CakeRolePermit> queryWrapper = new LambdaQueryWrapper<>();
            for (Long permitId : cakeRolePermitVo.getCakePermitIdList()) {
                queryWrapper.eq(CakeRolePermit::getCakePermitId, permitId).eq(CakeRolePermit::getCakeRoleId, cakeRoleId);
                baseMapper.delete(queryWrapper);
                queryWrapper.clear();
            }
        } catch (Exception e) {
            return "删除失败,服务器错误";

        }
        return "删除存在权限成功";
    }
}
