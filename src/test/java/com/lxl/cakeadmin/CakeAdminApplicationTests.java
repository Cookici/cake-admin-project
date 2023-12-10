package com.lxl.cakeadmin;

import com.alibaba.fastjson2.JSON;
import com.lxl.cakeadmin.entity.CakeLabel;
import com.lxl.cakeadmin.security.WhiteList;
import com.lxl.cakeadmin.service.CakeLabelService;
import com.lxl.cakeadmin.service.CakeUserService;
import com.lxl.cakeadmin.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.AntPathMatcher;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CakeAdminApplicationTests {

    @Autowired
    private CakeLabelService cakeLabelService;

    @Autowired
    private CakeUserService cakeUserService;

    @Test
    void contextLoads() {
        List<CakeLabel> list = cakeLabelService.list();
        list.forEach(System.out::println);
    }

    @Test
    void test01() {
        String url1 = "/swagger-ui/index.html";
        String url2 = "/swagger-resources/1/2";

        System.out.println(url1 + " is allowed: " + isUrlAllowed(url1));
        System.out.println(url2 + " is allowed: " + isUrlAllowed(url2));
    }

    public static boolean isUrlAllowed(String url) {
        AntPathMatcher pathMatcher = new AntPathMatcher();

        for (String pattern : WhiteList.URL_WHITELIST) {
            if (pathMatcher.match(pattern, url)) {
                return true;
            }
        }

        return false;
    }


    @Test
    void test02() {
        String s = "[\"admin\",\"user\"]";
        List<String> list = new ArrayList<>();
        list.add("admin");
        list.add("user");
        List<String> strings = JSON.parseArray(s, String.class);
        // String jsonString = JSON.toJSONString(list);

        JwtUtils jwtUtils = new JwtUtils();

        System.out.println(jwtUtils.generateToken("user"));

    }

    @Test
    public void test03() throws URISyntaxException {

        String old = "https://lrh-cake-project.oss-cn-beijing.aliyuncs.com/2023-12-09/af646566-a7dd-4290-aa79-2d2a5fc68290.jpg";
        URI uri = new URI(old);
        String path = uri.getPath();
        System.out.println("path ===> " + path);

    }

}
