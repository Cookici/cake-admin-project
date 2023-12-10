package com.lxl.cakeadmin.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.utils
 * @ClassName: PageUtils
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/7 14:10
 */
@Component
public class PageUtils<T,V> {

    public IPage<T> getVoPage(List<T> list, Page<V> page) {
        IPage<T> cakeUserVoPage = new Page<>();
        cakeUserVoPage.setRecords(list);
        cakeUserVoPage.setSize(page.getSize());
        cakeUserVoPage.setCurrent(page.getCurrent());
        cakeUserVoPage.setTotal(page.getTotal());
        cakeUserVoPage.setPages(page.getPages());
        return cakeUserVoPage;
    }


}
