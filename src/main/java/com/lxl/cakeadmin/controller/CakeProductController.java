package com.lxl.cakeadmin.controller;

import com.aliyun.oss.OSS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lxl.cakeadmin.entity.CakeProduct;
import com.lxl.cakeadmin.entity.CakeRole;
import com.lxl.cakeadmin.entity.CakeUser;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakeProductService;
import com.lxl.cakeadmin.service.OssService;
import com.lxl.cakeadmin.utils.PageUtils;
import com.lxl.cakeadmin.utils.TransObjectUtils;
import com.lxl.cakeadmin.vo.CakeProductVo;
import com.lxl.cakeadmin.vo.CakeUserVo;
import com.lxl.cakeadmin.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.controller
 * @ClassName: CakeProductController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/7 12:31
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class CakeProductController {

    @Autowired
    private CakeProductService cakeProductService;

    @Autowired
    private OssService ossService;

    @Autowired
    private TransObjectUtils transObjectUtils;

    @Autowired
    private PageUtils<CakeProductVo, CakeProduct> pageUtils;

    @GetMapping("/list")
    public Result<IPage<CakeProductVo>> getProductList(PageVo pageVo) {
        log.info("keyword ===> {}", pageVo.getKeyword());
        Page<CakeProduct> page;
        List<CakeProductVo> cakeProductVoList = new ArrayList<>();
        if (Objects.equals(pageVo.getKeyword(), "")) {
            page = cakeProductService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()), new LambdaQueryWrapper<CakeProduct>().orderByDesc(CakeProduct::getCreateTime));
            List<CakeProduct> cakeProductList = page.getRecords();
            for (CakeProduct cakeProduct : cakeProductList) {
                CakeProductVo cakeProductVo = transObjectUtils.cakeProductTransToCakeProductVo(cakeProduct);
                cakeProductVoList.add(cakeProductVo);
            }
        } else {
            page = cakeProductService.page(new Page<>(pageVo.getCurrent(), pageVo.getSize()), new LambdaQueryWrapper<CakeProduct>().orderByDesc(CakeProduct::getCreateTime).like(CakeProduct::getCakeProductName, pageVo.getKeyword()));
            List<CakeProduct> cakeProductList = page.getRecords();
            for (CakeProduct cakeProduct : cakeProductList) {
                CakeProductVo cakeProductVo = transObjectUtils.cakeProductTransToCakeProductVo(cakeProduct);
                cakeProductVoList.add(cakeProductVo);
            }
        }
        IPage<CakeProductVo> cakeUserVoPage = pageUtils.getVoPage(cakeProductVoList, page);
        return Result.ok(cakeUserVoPage);
    }

    @PostMapping("/add")
    public Result<String> addProduct(@RequestBody CakeProduct cakeProduct) {
        boolean save = cakeProductService.save(cakeProduct);
        if (save) {
            return Result.ok("添加成功");
        }
        return Result.fail("添加失败");
    }

    @PutMapping("/edit")
    public Result<String> updateProduct(@RequestBody CakeProduct cakeProduct) {
        boolean update = cakeProductService.updateById(cakeProduct);
        if (update) {

            return Result.ok("更新成功");
        }
        return Result.fail("更新失败");
    }

    @DeleteMapping("/delete")
    public Result<String> removeProduct(@RequestParam Long id) {
        CakeProduct cakeProduct = cakeProductService.getById(id);
        boolean remove = cakeProductService.removeById(id);
        if (remove) {
            String needUrl = "\"" + cakeProduct.getCakeProductPhoto() + "\"";
            log.info("delete product url ===> {}", needUrl);
            ossService.deleteOneFile(needUrl);
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }


    @DeleteMapping("/deleteList")
    public Result<String> removeProductByIds(@RequestParam List<Long> ids) {
        List<CakeProduct> cakeProducts = cakeProductService.listByIds(ids);
        List<String> urlList = new ArrayList<>();
        for (CakeProduct cakeProduct : cakeProducts) {
            urlList.add("\"" + cakeProduct.getCakeProductPhoto() + "\"");
        }
        boolean remove = cakeProductService.removeByIds(ids);
        if (remove) {
            ossService.deleteNotOneFile(urlList);
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");
    }

}
