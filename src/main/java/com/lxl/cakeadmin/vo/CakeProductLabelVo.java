package com.lxl.cakeadmin.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.vo
 * @ClassName: CakeProductLabelVo
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/11 16:48
 */
@Data
public class CakeProductLabelVo {

    private Long cakeProductId;

    private List<Long> cakeLabelIdList;

}
