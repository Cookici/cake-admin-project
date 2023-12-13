package com.lxl.cakeadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.cakeadmin.entity.CakePermit;
import com.lxl.cakeadmin.entity.CakeRole;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakePermitService;
import com.lxl.cakeadmin.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @PreAuthorize("hasAnyAuthority('permit:list') or hasAnyAuthority('permit:*')")
    @GetMapping("/list")
    public Result<IPage<CakePermit>> getPermitList(PageVo pageVo) {

        Page<CakePermit> page;
        if (Objects.equals(pageVo.getKeyword(), "")) {
           page = cakePermitService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()),new LambdaQueryWrapper<CakePermit>().orderByDesc(CakePermit::getCreateTime));
        } else {
            page = cakePermitService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()), new LambdaQueryWrapper<CakePermit>().like(CakePermit::getCakePermitName, pageVo.getKeyword()).orderByDesc(CakePermit::getCreateTime));
        }

        return Result.ok(page);
    }


    @PreAuthorize("hasAnyAuthority('permit:add') or hasAnyAuthority('permit:*')")
    @PostMapping("/add")
    public Result<String> addPermit(@RequestBody CakePermit cakePermit){
        boolean save = cakePermitService.save(cakePermit);
        if(save){
            return Result.ok("添加成功");
        }
        return Result.fail("添加失败");
    }


    @PreAuthorize("hasAnyAuthority('permit:edit') or hasAnyAuthority('permit:*')")
    @PutMapping("/edit")
    public Result<String> updatePermit(@RequestBody CakePermit cakePermit){
        boolean update = cakePermitService.updateById(cakePermit);
        if(update){
            return Result.ok("更新成功");
        }
        return Result.fail("更新失败");
    }


    @PreAuthorize("hasAnyAuthority('permit:delete') or hasAnyAuthority('permit:*')")
    @DeleteMapping("/delete")
    public Result<String> removePermit(@RequestParam Long id){
        boolean remove = cakePermitService.removeById(id);
        if(remove){
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PreAuthorize("hasAnyAuthority('permit:deleteList') or hasAnyAuthority('permit:*')")
    @DeleteMapping("/deleteList")
    public Result<String> removePermitByIds(@RequestParam List<Long> ids){
        boolean remove = cakePermitService.removeByIds(ids);
        if(remove){
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }


}
