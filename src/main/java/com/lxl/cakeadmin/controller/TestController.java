package com.lxl.cakeadmin.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.controller
 * @ClassName: TestController
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/6 20:21
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PreAuthorize("hasAnyAuthority('test:admin')")
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @PreAuthorize("hasAnyAuthority('test:user')")
    @GetMapping("/user")
    public String user(){
        return "user";
    }

}
