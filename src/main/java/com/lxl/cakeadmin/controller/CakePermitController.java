package com.lxl.cakeadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.cakeadmin.entity.CakePermit;
import com.lxl.cakeadmin.entity.CakeRole;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakePermitService;
import com.lxl.cakeadmin.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    public Result<String> addPermit(@RequestBody CakePermit cakePermit){
        boolean save = cakePermitService.save(cakePermit);
        if(save){
            return Result.ok("添加成功");
        }
        return Result.fail("添加失败");
    }

    @PutMapping("/edit")
    public Result<String> updatePermit(@RequestBody CakePermit cakePermit){
        boolean update = cakePermitService.updateById(cakePermit);
        if(update){
            return Result.ok("更新成功");
        }
        return Result.fail("更新失败");
    }

    @DeleteMapping("/delete")
    public Result<String> removePermit(@RequestParam Long id){
        boolean remove = cakePermitService.removeById(id);
        if(remove){
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

    @DeleteMapping("/deleteList")
    public Result<String> removePermitByIds(@RequestParam List<Long> ids){
        boolean remove = cakePermitService.removeByIds(ids);
        if(remove){
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }


}
