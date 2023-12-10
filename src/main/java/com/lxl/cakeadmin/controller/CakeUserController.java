package com.lxl.cakeadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakeUserService;
import com.lxl.cakeadmin.utils.PageUtils;
import com.lxl.cakeadmin.utils.TransObjectUtils;
import com.lxl.cakeadmin.vo.CakeUserVo;
import com.lxl.cakeadmin.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.controller
 * @ClassName: CakeUserController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/7 12:32
 */
@RestController
@RequestMapping("/user")
public class CakeUserController {

    @Autowired
    private CakeUserService cakeUserService;

    @Autowired
    private TransObjectUtils transObjectUtils;

    @Autowired
    private PageUtils<CakeUserVo, CakeUser> pageUtils;


    @GetMapping("/list")
    public Result<IPage<CakeUserVo>> getUserList(PageVo pageVo) {
        Page<CakeUser> page = cakeUserService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()));
        List<CakeUser> cakeUserList = page.getRecords();
        List<CakeUserVo> cakeUserVoList = new ArrayList<>();
        for (CakeUser cakeUser : cakeUserList) {
            CakeUserVo cakeUserVo = transObjectUtils.cakeUserTransToCakeUserVo(cakeUser);
            cakeUserVoList.add(cakeUserVo);
        }
        IPage<CakeUserVo> cakeUserVoPage = pageUtils.getVoPage(cakeUserVoList, page);
        return Result.ok(cakeUserVoPage);
    }


}


