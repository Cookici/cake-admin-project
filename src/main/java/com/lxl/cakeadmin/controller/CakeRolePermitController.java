package com.lxl.cakeadmin.controller;

import com.lxl.cakeadmin.aop.SensitiveOperations;
import com.lxl.cakeadmin.entity.CakePermit;
import com.lxl.cakeadmin.entity.CakeProductLabel;
import com.lxl.cakeadmin.entity.CakeRolePermit;
import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.CakeProductLabelService;
import com.lxl.cakeadmin.service.CakeRolePermitService;
import com.lxl.cakeadmin.vo.CakeProductLabelVo;
import com.lxl.cakeadmin.vo.CakeRolePermitVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.controller
 * @ClassName: CakeRolePermitController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/13 14:27
 */
@Slf4j
@RestController
@RequestMapping("/role-permit")
public class CakeRolePermitController {
    @Autowired
    private CakeRolePermitService cakeRolePermitService;

    @SensitiveOperations("permit")
    @PreAuthorize("hasAnyAuthority('role-permit:add') or hasAnyAuthority('role-permit:*')")
    @PostMapping("/add")
    public Result<String> addRolePermit(@RequestBody CakeRolePermitVo cakeRolePermitVo) {

        log.info("cakeRolePermitVo ====> {}", cakeRolePermitVo);

        List<CakeRolePermit> cakeRolePermitList = new ArrayList<>();
        for (Long permitId : cakeRolePermitVo.getCakePermitIdList()) {
            CakeRolePermit cakeRolePermit = new CakeRolePermit();
            cakeRolePermit.setCakeRoleId(cakeRolePermitVo.getCakeRoleId());
            cakeRolePermit.setCakePermitId(permitId);
            cakeRolePermitList.add(cakeRolePermit);
        }

        if (cakeRolePermitVo.getCakePermitIdList().isEmpty()) {
            return Result.ok("已存在全部");
        }
        boolean add = cakeRolePermitService.saveBatch(cakeRolePermitList);
        if (add) {
            return Result.ok("添加未存在权限成功");
        }
        return Result.fail("添加失败");
    }

    @SensitiveOperations("permit")
    @PreAuthorize("hasAnyAuthority('role-permit:delete') or hasAnyAuthority('role-permit:*')")
    @DeleteMapping("/delete")
    public Result<String> deleteProductLabel(@RequestBody CakeRolePermitVo cakeRolePermitVo) {

        log.info("cakeRolePermitVo ====> {}", cakeRolePermitVo);

        String dataMessage = cakeRolePermitService.deleteList(cakeRolePermitVo);

        return Result.ok(dataMessage);
    }
}
