package com.lxl.cakeadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.cakeadmin.entity.CakeRole;
import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakeRoleService;
import com.lxl.cakeadmin.utils.PageUtils;
import com.lxl.cakeadmin.vo.CakeUserVo;
import com.lxl.cakeadmin.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.controller
 * @ClassName: CakeRoleController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/7 12:31
 */
@RestController
@RequestMapping("/role")
public class CakeRoleController {

    @Autowired
    private CakeRoleService cakeRoleService;

    @GetMapping("/list")
    public Result<IPage<CakeRole>> getRoleList(PageVo pageVo) {
        Page<CakeRole> page = cakeRoleService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()));
        return Result.ok(page);
    }


}
