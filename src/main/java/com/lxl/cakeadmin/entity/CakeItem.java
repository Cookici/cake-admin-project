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

@ApiModel(description = "cake_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cake_item")
public class CakeItem {
    /**
     * 自增ID
     */
    @TableId(value = "cake_item_id", type = IdType.INPUT)
    @ApiModelProperty(value = "自增ID")
    private Long cakeItemId;

    /**
     * 配料名称
     */
    @TableField(value = "cake_item_name")
    @ApiModelProperty(value = "配料名称")
    private String cakeItemName;

    /**
     * 配料价格
     */
    @TableField(value = "cake_item_price")
    @ApiModelProperty(value = "配料价格")
    private Long cakeItemPrice;

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