package com.lxl.cakeadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.cakeadmin.entity.CakeLabel;
import com.lxl.cakeadmin.entity.CakePermit;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakeLabelService;
import com.lxl.cakeadmin.service.CakePermitService;
import com.lxl.cakeadmin.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.controller
 * @ClassName: CakeLabelController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/7 12:31
 */
@RestController
@RequestMapping("/label")
public class CakeLabelController {

    @Autowired
    private CakeLabelService cakeLabelService;

    @GetMapping("/list")
    public Result<IPage<CakeLabel>> getPermitList(PageVo pageVo) {
        Page<CakeLabel> page = cakeLabelService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()));
        return Result.ok(page);
    }

}
