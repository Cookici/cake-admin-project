package com.lxl.cakeadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "cake_product_label")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cake_product_label")
public class CakeProductLabel {
    /**
     * 自增ID
     */
    @TableId(value = "product_label_id", type = IdType.INPUT)
    @ApiModelProperty(value = "自增ID")
    private Long productLabelId;

    /**
     * 蛋糕ID
     */
    @TableField(value = "cake_product_id")
    @ApiModelProperty(value = "蛋糕ID")
    private Long cakeProductId;

    /**
     * 标签ID
     */
    @TableField(value = "cake_label_id")
    @ApiModelProperty(value = "标签ID")
    private Long cakeLabelId;
}