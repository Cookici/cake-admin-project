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

import java.util.Date;

@ApiModel(description="cake_permit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cake_permit")
public class CakePermit {
    /**
     * 权限ID
     */
    @TableId(value = "cake_permit_id", type = IdType.INPUT)
    @ApiModelProperty(value="权限ID")
    private Long cakePermitId;

    @TableField(value = "cake_permit_name")
    @ApiModelProperty(value="权限名称")
    private String cakePermitName;

    @TableField(value = "cake_permit_description")
    @ApiModelProperty(value="权限名称")
    private String cakePermitDescription;


    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}