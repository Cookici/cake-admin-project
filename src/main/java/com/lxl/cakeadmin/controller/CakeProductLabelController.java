package com.lxl.cakeadmin.controller;

import com.lxl.cakeadmin.entity.CakeProduct;
import com.lxl.cakeadmin.entity.CakeProductLabel;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakeProductLabelService;
import com.lxl.cakeadmin.vo.CakeProductLabelVo;
import com.lxl.cakeadmin.vo.CakeProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.controller
 * @ClassName: CakeProductLabelController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/11 15:46
 */
@Slf4j
@RestController
@RequestMapping("/product-label")
public class CakeProductLabelController {

    @Autowired
    private CakeProductLabelService cakeProductLabelService;

    @PreAuthorize("hasAnyAuthority('product-label:add') or hasAnyAuthority('product-label:*')")
    @PostMapping("/add")
    public Result<String> addProductLabel(@RequestBody CakeProductLabelVo cakeProductLabelVo) {

        log.info("cakeProductLabelVo ====> {}", cakeProductLabelVo);

        List<CakeProductLabel> cakeProductList = new ArrayList<>();
        for (Long labelId : cakeProductLabelVo.getCakeLabelIdList()) {
            CakeProductLabel cakeProductLabel = new CakeProductLabel();
            cakeProductLabel.setCakeProductId(cakeProductLabelVo.getCakeProductId());
            cakeProductLabel.setCakeLabelId(labelId);
            cakeProductList.add(cakeProductLabel);
        }

        if (cakeProductLabelVo.getCakeLabelIdList().isEmpty()) {
            return Result.ok("已存在全部");
        }
        boolean add = cakeProductLabelService.saveBatch(cakeProductList);
        if (add) {
            return Result.ok("添加未存在标签成功");
        }
        return Result.fail("添加失败");
    }

    @PreAuthorize("hasAnyAuthority('product-label:delete' or hasAnyAuthority('product-label:*'))")
    @DeleteMapping("/delete")
    public Result<String> deleteProductLabel(@RequestBody CakeProductLabelVo cakeProductLabelVo) {

        log.info("cakeProductLabelVo ====> {}", cakeProductLabelVo);

        String dataMessage = cakeProductLabelService.deleteList(cakeProductLabelVo);

        return Result.ok(dataMessage);
    }


}
