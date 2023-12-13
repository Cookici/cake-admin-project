package com.lxl.cakeadmin.vo;


import com.lxl.cakeadmin.entity.CakePermit;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.vo
 * @ClassName: CakeRoleVo
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/13 11:29
 */
@Data
public class CakeRoleVo {

    private Integer cakeRoleId;


    private String cakeRoleName;


    private String cakeRoleDescription;


    private Integer cakeRoleLevel;

    private List<CakePermit> cakePermitList;

    private Date createTime;
}
