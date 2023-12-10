package com.lxl.cakeadmin.controller;

import com.lxl.cakeadmin.result.Result;
import com.lxl.cakeadmin.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.config
 * @ClassName: OssController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/8 17:40
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/oss")
public class OssController {


    @Autowired
    private OssService ossService;


    @PostMapping("/policy")
    public Result<Map<String, String>> policy() {
        Map<String, String> result = ossService.returnPolicy();
        return Result.ok(result);
    }

    @DeleteMapping("/deleteFile")
    public Result<String> deleteFileByFileUrl(@RequestBody String deletePhotoUrl) {
       String message = ossService.deleteOneFile(deletePhotoUrl);
        return Result.ok(message);
    }
}
