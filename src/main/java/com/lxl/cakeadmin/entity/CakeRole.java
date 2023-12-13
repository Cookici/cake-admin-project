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

@ApiModel(description="cake_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cake_role")
public class CakeRole {
    /**
     * 角色ID
     */
    @TableId(value = "cake_role_id", type = IdType.INPUT)
    @ApiModelProperty(value="角色ID")
    private Integer cakeRoleId;

    /**
     * 角色名称
     */
    @TableField(value = "cake_role_name")
    @ApiModelProperty(value="角色名称")
    private String cakeRoleName;

    @TableField(value = "cake_role_description")
    @ApiModelProperty(value="角色名称")
    private String cakeRoleDescription;


    @TableField(value = "cake_role_level")
    @ApiModelProperty(value="角色等级")
    private Integer cakeRoleLevel;


    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}