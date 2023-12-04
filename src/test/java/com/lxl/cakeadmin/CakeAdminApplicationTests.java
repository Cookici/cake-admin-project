package com.lxl.cakeadmin;

import com.lxl.cakeadmin.entity.CakeLabel;
import com.lxl.cakeadmin.service.CakeLabelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CakeAdminApplicationTests {

    @Autowired
    private CakeLabelService cakeLabelService;

    @Test
    void contextLoads() {
        List<CakeLabel> list = cakeLabelService.list();
        list.forEach(System.out::println);
    }

}
