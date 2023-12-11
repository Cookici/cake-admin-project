package com.lxl.cakeadmin.vo;

import com.lxl.cakeadmin.entity.CakeLabel;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.vo
 * @ClassName: CakeProductVo
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/11 8:11
 */
@Data
public class CakeProductVo {

    private Long cakeProductId;
    private String cakeProductName;
    private String cakeProductPhoto;
    private Long cakeProductPrice;
    private Date createTime;
    private List<CakeLabel> cakeLabelList;

}
