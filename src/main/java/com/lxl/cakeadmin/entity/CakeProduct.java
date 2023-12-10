package com.lxl.cakeadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "cake_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cake_product")
public class CakeProduct {
    /**
     * 自增ID
     */
    @TableId(value = "cake_product_id", type = IdType.INPUT)
    @ApiModelProperty(value = "自增ID")
    private Long cakeProductId;

    /**
     * 蛋糕名称
     */
    @TableField(value = "cake_product_name")
    @ApiModelProperty(value = "蛋糕名称")
    private String cakeProductName;

    /**
     * 蛋糕图片
     */
    @TableField(value = "cake_product_photo")
    @ApiModelProperty(value = "蛋糕图片")
    private String cakeProductPhoto;

    /**
     * 蛋糕价格
     */
    @TableField(value = "cake_product_price")
    @ApiModelProperty(value = "蛋糕价格")
    private Long cakeProductPrice;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}