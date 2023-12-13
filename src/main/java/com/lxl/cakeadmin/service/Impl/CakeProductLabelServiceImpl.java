package com.lxl.cakeadmin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.vo.CakeProductLabelVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxl.cakeadmin.mapper.CakeProductLabelMapper;
import com.lxl.cakeadmin.entity.CakeProductLabel;
import com.lxl.cakeadmin.service.CakeProductLabelService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CakeProductLabelServiceImpl extends ServiceImpl<CakeProductLabelMapper, CakeProductLabel> implements CakeProductLabelService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteList(CakeProductLabelVo cakeProductLabelVo) {


        if (cakeProductLabelVo.getCakeLabelIdList().isEmpty()) {
            return "未包含已有标签";
        }

        try {
            Long cakeProductId = cakeProductLabelVo.getCakeProductId();
            LambdaQueryWrapper<CakeProductLabel> queryWrapper = new LambdaQueryWrapper<>();
            for (Long labelId : cakeProductLabelVo.getCakeLabelIdList()) {
                queryWrapper.eq(CakeProductLabel::getCakeLabelId, labelId).eq(CakeProductLabel::getCakeProductId, cakeProductId);
                baseMapper.delete(queryWrapper);
                queryWrapper.clear();
            }
        } catch (Exception e) {
            return "删除失败,服务器错误";

        }
        return "删除存在标签成功";
    }
}
