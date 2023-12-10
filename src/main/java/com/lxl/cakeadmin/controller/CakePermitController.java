package com.lxl.cakeadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.cakeadmin.entity.CakePermit;
import com.lxl.cakeadmin.entity.CakeRole;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakePermitService;
import com.lxl.cakeadmin.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.controller
 * @ClassName: CakePermitController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/7 12:31
 */
@RestController
@RequestMapping("/permit")
public class CakePermitController {

    @Autowired
    private CakePermitService cakePermitService;

    @GetMapping("/list")
    public Result<IPage<CakePermit>> getPermitList(PageVo pageVo) {
        Page<CakePermit> page = cakePermitService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()));
        return Result.ok(page);
    }

}
