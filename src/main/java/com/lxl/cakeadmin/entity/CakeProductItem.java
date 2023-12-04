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

@ApiModel(description = "cake_product_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cake_product_item")
public class CakeProductItem {
    /**
     * 自增ID
     */
    @TableId(value = "product_item_id", type = IdType.INPUT)
    @ApiModelProperty(value = "自增ID")
    private Long productItemId;

    /**
     * 蛋糕ID
     */
    @TableField(value = "cake_product_id")
    @ApiModelProperty(value = "蛋糕ID")
    private Long cakeProductId;

    /**
     * 配料ID
     */
    @TableField(value = "cake_item_id")
    @ApiModelProperty(value = "配料ID")
    private Long cakeItemId;
}