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

@ApiModel(description = "cake_label")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cake_label")
public class CakeLabel {
    /**
     * 自增ID
     */
    @TableId(value = "cake_label_id", type = IdType.INPUT)
    @ApiModelProperty(value = "自增ID")
    private Long cakeLabelId;

    /**
     * 标签名称
     */
    @TableField(value = "cake_label_name")
    @ApiModelProperty(value = "标签名称")
    private String cakeLabelName;

    /**
     * 标签样式
     */
    @TableField(value = "cake_label_icon")
    @ApiModelProperty(value = "标签样式")
    private String cakeLabelIcon;

    @TableField(value = "create_time")
    @ApiModelProperty(value = "")
    private Date createTime;

    @TableField(value = "update_time")
    @ApiModelProperty(value = "")
    private Date updateTime;
}