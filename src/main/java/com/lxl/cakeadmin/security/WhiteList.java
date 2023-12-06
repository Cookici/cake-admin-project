package com.lxl.cakeadmin.security;

/**
 * @ProjectName: Blog
 * @Package: com.lrh.blog.identify.filter
 * @ClassName: WhiteList
 * @Author: 63283
 * @Description:
 * @Date: 2023/11/17 10:53
 */

public interface WhiteList {

    String[] URL_WHITELIST = {
            "/login",
            "/logout",
            "/favicon.ico",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v3/api-docs",
            "/webjars/**"
    };

}
