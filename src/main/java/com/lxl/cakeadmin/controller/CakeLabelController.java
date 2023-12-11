package com.lxl.cakeadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.cakeadmin.entity.CakeLabel;
import com.lxl.cakeadmin.entity.CakePermit;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakeLabelService;
import com.lxl.cakeadmin.service.CakePermitService;
import com.lxl.cakeadmin.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.controller
 * @ClassName: CakeLabelController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/7 12:31
 */
@Slf4j
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

    @PostMapping("/add")
    public Result<String> addLabel(@RequestBody CakeLabel cakeLabel){
        boolean save = cakeLabelService.save(cakeLabel);
        if(save){
            return Result.ok("添加成功");
        }
        return Result.fail("添加失败");
    }

    @PutMapping("/edit")
    public Result<String> updateLabel(@RequestBody CakeLabel cakeLabel){
        boolean update = cakeLabelService.updateById(cakeLabel);
        if(update){
            return Result.ok("更新成功");
        }
        return Result.fail("更新失败");
    }

    @DeleteMapping("/delete")
    public Result<String> removeLabel(@RequestParam Long id){
        boolean remove = cakeLabelService.removeById(id);
        if(remove){
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

    @DeleteMapping("/deleteList")
    public Result<String> removeLabelByIds(@RequestParam List<Long> ids){
        boolean remove = cakeLabelService.removeByIds(ids);
        if (remove){
            return Result.ok("删除成功");
        }
        return  Result.fail("删除失败");
    }

}
