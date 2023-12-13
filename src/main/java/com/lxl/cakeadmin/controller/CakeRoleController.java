package com.lxl.cakeadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.cakeadmin.aop.SensitiveOperations;
import com.lxl.cakeadmin.entity.CakeLabel;
import com.lxl.cakeadmin.entity.CakeProduct;
import com.lxl.cakeadmin.entity.CakeRole;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakeRoleService;
import com.lxl.cakeadmin.utils.PageUtils;
import com.lxl.cakeadmin.utils.TransObjectUtils;
import com.lxl.cakeadmin.vo.CakeProductVo;
import com.lxl.cakeadmin.vo.CakeRoleVo;
import com.lxl.cakeadmin.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private TransObjectUtils transObjectUtils;

    @Autowired
    private PageUtils<CakeRoleVo, CakeRole> pageUtils;

    @PreAuthorize("hasAnyAuthority('role:list') or hasAnyAuthority('role:*')")
    @GetMapping("/list")
    public Result<IPage<CakeRoleVo>> getRoleList(PageVo pageVo) {
        Page<CakeRole> page;
        List<CakeRoleVo> cakeRoleVoList = new ArrayList<>();
        if (Objects.equals(pageVo.getKeyword(), "")) {
            page = cakeRoleService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()), new LambdaQueryWrapper<CakeRole>().orderByDesc(CakeRole::getCreateTime));
            List<CakeRole> cakeRoleList = page.getRecords();
            for (CakeRole cakeRole : cakeRoleList) {
                CakeRoleVo cakeRoleVo = transObjectUtils.cakeRoleTransToCakeRoleVo(cakeRole);
                cakeRoleVoList.add(cakeRoleVo);
            }
        } else {
            page = cakeRoleService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()), new LambdaQueryWrapper<CakeRole>().like(CakeRole::getCakeRoleName, pageVo.getKeyword()).orderByDesc(CakeRole::getCreateTime));
            List<CakeRole> cakeRoleList = page.getRecords();
            for (CakeRole cakeRole : cakeRoleList) {
                CakeRoleVo cakeRoleVo = transObjectUtils.cakeRoleTransToCakeRoleVo(cakeRole);
                cakeRoleVoList.add(cakeRoleVo);
            }
        }
        IPage<CakeRoleVo> cakeRoleVoPage = pageUtils.getVoPage(cakeRoleVoList, page);
        return Result.ok(cakeRoleVoPage);
    }

    @SensitiveOperations("role")
    @PreAuthorize("hasAnyAuthority('role:add') or hasAnyAuthority('role:*')")
    @PostMapping("/add")
    public Result<String> addRole(@RequestBody CakeRole cakeRole) {
        boolean save = cakeRoleService.save(cakeRole);
        if (save) {
            return Result.ok("添加成功");
        }
        return Result.fail("添加失败");
    }

    @SensitiveOperations("role")
    @PreAuthorize("hasAnyAuthority('role:edit') or hasAnyAuthority('role:*')")
    @PutMapping("/edit")
    public Result<String> updateRole(@RequestBody CakeRole cakeRole) {
        boolean update = cakeRoleService.updateById(cakeRole);
        if (update) {
            return Result.ok("更新成功");
        }
        return Result.fail("更新失败");
    }

    @SensitiveOperations("role")
    @PreAuthorize("hasAnyAuthority('role:delete') or hasAnyAuthority('role:*')")
    @DeleteMapping("/delete")
    public Result<String> removeRole(@RequestParam Long id) {
        boolean remove = cakeRoleService.removeById(id);
        if (remove) {
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

    @SensitiveOperations("role")
    @PreAuthorize("hasAnyAuthority('role:deleteList') or hasAnyAuthority('role:*')")
    @DeleteMapping("/deleteList")
    public Result<String> removeRoleByIds(@RequestParam List<Long> ids) {
        boolean remove = cakeRoleService.removeByIds(ids);
        if (remove) {
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

}
