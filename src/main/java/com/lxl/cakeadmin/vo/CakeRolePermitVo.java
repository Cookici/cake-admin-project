package com.lxl.cakeadmin.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.vo
 * @ClassName: CakeRolePermitVo
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/13 14:29
 */
@Data
public class CakeRolePermitVo {


        private Integer cakeRoleId;

        private List<Long> cakePermitIdList;

}
