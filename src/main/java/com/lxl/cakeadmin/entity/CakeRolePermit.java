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

@ApiModel(description = "cake_role_permit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cake_role_permit")
public class CakeRolePermit {
    /**
     * 自增ID
     */
    @TableId(value = "role_permit_id", type = IdType.INPUT)
    @ApiModelProperty(value = "自增ID")
    private Long rolePermitId;

    /**
     * 权限ID
     */
    @TableField(value = "cake_permit_id")
    @ApiModelProperty(value = "权限ID")
    private Long cakePermitId;

    /**
     * 角色ID
     */
    @TableField(value = "cake_role_id")
    @ApiModelProperty(value = "角色ID")
    private Integer cakeRoleId;
}