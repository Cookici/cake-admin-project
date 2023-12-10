package com.lxl.cakeadmin.vo;

import lombok.Data;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.vo
 * @ClassName: PageVo
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/7 14:00
 */
@Data
public class PageVo {

    private Integer current;

    private Integer size;

    private String keyword;

}
