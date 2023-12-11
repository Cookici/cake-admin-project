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
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public Result<String> addRole(@RequestBody CakeRole cakeRole){
        boolean save = cakeRoleService.save(cakeRole);
        if(save){
            return Result.ok("添加成功");
        }
        return Result.fail("添加失败");
    }

    @PutMapping("/edit")
    public Result<String> updateRole(@RequestBody CakeRole cakeRole){
        boolean update = cakeRoleService.updateById(cakeRole);
        if(update){
            return Result.ok("更新成功");
        }
        return Result.fail("更新失败");
    }

    @DeleteMapping("/delete")
    public Result<String> removeRole(@RequestParam Long id){
        boolean remove = cakeRoleService.removeById(id);
        if (remove){
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

    @DeleteMapping("/deleteList")
    public Result<String> removeRoleByIds(@RequestParam List<Long> ids){
        boolean remove = cakeRoleService.removeByIds(ids);
        if (remove){
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

}
