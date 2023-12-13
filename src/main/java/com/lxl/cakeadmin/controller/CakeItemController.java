package com.lxl.cakeadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.cakeadmin.entity.CakeItem;
import com.lxl.cakeadmin.entity.CakeProduct;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakeItemService;
import com.lxl.cakeadmin.service.OssService;
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
 * @ClassName: CakeItemController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/7 12:31
 */
@Slf4j
@RestController
@RequestMapping("/item")
public class CakeItemController {

    @Autowired
    private CakeItemService cakeItemService;

    @Autowired
    private OssService ossService;

    @PreAuthorize("hasAnyAuthority('item:list') or hasAnyAuthority('item:*')")
    @GetMapping("/list")
    public Result<IPage<CakeItem>> getPermitList(PageVo pageVo) {
        log.info("keyword ===> {}", pageVo.getKeyword());
        Page<CakeItem> page;
        if (Objects.equals(pageVo.getKeyword(), "")) {
            page = cakeItemService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()), new LambdaQueryWrapper<CakeItem>().orderByDesc(CakeItem::getCreateTime));
        } else {
            page = cakeItemService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()), new LambdaQueryWrapper<CakeItem>().orderByDesc(CakeItem::getCreateTime).like(CakeItem::getCakeItemName, pageVo.getKeyword()));
        }
        return Result.ok(page);
    }

    @PreAuthorize("hasAnyAuthority('item:add') or hasAnyAuthority('item:*')")
    @PostMapping("/add")
    public Result<String> addItem(@RequestBody CakeItem cakeItem) {
        boolean save = cakeItemService.save(cakeItem);
        if (save) {
            return Result.ok("添加成功");
        }
        return Result.fail("添加失败");
    }

    @PreAuthorize("hasAnyAuthority('item:edit') or hasAnyAuthority('item:*')")
    @PutMapping("/edit")
    public Result<String> updateItem(@RequestBody CakeItem cakeItem) {
        boolean update = cakeItemService.updateById(cakeItem);
        if (update) {
            return Result.ok("更新成功");
        }
        return Result.fail("更新失败");
    }


    @PreAuthorize("hasAnyAuthority('item:delete') or hasAnyAuthority('item:*')")
    @DeleteMapping("/delete")
    public Result<String> removeItem(@RequestParam Long id) {
        CakeItem cakeItem = cakeItemService.getById(id);
        boolean remove = cakeItemService.removeById(id);
        if (remove) {
            String needUrl = "\"" + cakeItem.getCakeItemId() + "\"";
            log.info("delete product url ===> {}", needUrl);
            ossService.deleteOneFile(needUrl);
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PreAuthorize("hasAnyAuthority('item:deletList') or hasAnyAuthority('item:*')")
    @DeleteMapping("/deleteList")
    public Result<String> removeItemByIds(@RequestParam List<Long> ids) {
        List<CakeItem> cakeItems = cakeItemService.listByIds(ids);
        List<String> urlList = new ArrayList<>();
        for (CakeItem cakeItem : cakeItems) {
            urlList.add("\"" + cakeItem.getCakeItemPhoto() + "\"");
        }
        boolean remove = cakeItemService.removeByIds(ids);
        if (remove) {
            ossService.deleteNotOneFile(urlList);
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

}
