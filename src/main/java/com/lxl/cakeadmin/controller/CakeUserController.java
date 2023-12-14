package com.lxl.cakeadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.cakeadmin.aop.SensitiveOperations;
import com.lxl.cakeadmin.entity.CakeProduct;
import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakeUserService;
import com.lxl.cakeadmin.utils.PageUtils;
import com.lxl.cakeadmin.utils.TransObjectUtils;
import com.lxl.cakeadmin.vo.CakeProductVo;
import com.lxl.cakeadmin.vo.CakeUserVo;
import com.lxl.cakeadmin.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.controller
 * @ClassName: CakeUserController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/7 12:32
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class CakeUserController {

    @Autowired
    private CakeUserService cakeUserService;

    @Autowired
    private TransObjectUtils transObjectUtils;

    @Autowired
    private PageUtils<CakeUserVo, CakeUser> pageUtils;

    @PreAuthorize("hasAnyAuthority('user:list') or hasAnyAuthority('user:*')")
    @GetMapping("/list")
    public Result<IPage<CakeUserVo>> getUserList(PageVo pageVo) {
        log.info("keyword ===> {}", pageVo.getKeyword());
        Page<CakeUser> page;
        List<CakeUserVo> cakeUserVoList = new ArrayList<>();
        if (Objects.equals(pageVo.getKeyword(), "")) {
            page = cakeUserService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()));
            List<CakeUser> cakeUserList = page.getRecords();
            for (CakeUser cakeUser : cakeUserList) {
                CakeUserVo cakeUserVo = transObjectUtils.cakeUserTransToCakeUserVo(cakeUser);
                cakeUserVoList.add(cakeUserVo);
            }
        } else {
            page = cakeUserService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()), new LambdaQueryWrapper<CakeUser>().orderByDesc(CakeUser::getCreateTime).like(CakeUser::getCakeUserUsername, pageVo.getKeyword()));
            List<CakeUser> cakeUserList = page.getRecords();
            for (CakeUser cakeUser : cakeUserList) {
                CakeUserVo cakeUserVo = transObjectUtils.cakeUserTransToCakeUserVo(cakeUser);
                cakeUserVoList.add(cakeUserVo);
            }
        }

        IPage<CakeUserVo> cakeUserVoPage = pageUtils.getVoPage(cakeUserVoList, page);
        return Result.ok(cakeUserVoPage);
    }

    @SensitiveOperations("user")
    @PreAuthorize("hasAnyAuthority('user:update') or hasAnyAuthority('user:*')")
    @PutMapping("/update")
    public Result<String> updateUserStatus(@RequestBody CakeUser cakeUser) {
        CakeUser nowCakeUser = new CakeUser();
        nowCakeUser.setCakeUserId(cakeUser.getCakeUserId());
        nowCakeUser.setCakeUserStatus(cakeUser.getCakeUserStatus());
        boolean update = cakeUserService.updateById(nowCakeUser);
        if (update) {
            return Result.ok("改变用户成功");
        }
        return Result.ok("改变用户失败");
    }


}


