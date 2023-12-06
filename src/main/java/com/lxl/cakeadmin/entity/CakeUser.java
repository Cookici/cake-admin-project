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

@ApiModel(description = "cake_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cake_user")
public class CakeUser {
    /**
     * 用户ID
     */
    @TableId(value = "cake_user_id", type = IdType.INPUT)
    @ApiModelProperty(value = "用户ID")
    private Long cakeUserId;

    /**
     * 用户账号
     */
    @TableField(value = "cake_user_username")
    @ApiModelProperty(value = "用户账号")
    private String cakeUserUsername;

    /**
     * 用户密码
     */
    @TableField(value = "cake_user_password")
    @ApiModelProperty(value = "用户密码")
    private String cakeUserPassword;

    /**
     * 用户昵称
     */
    @TableField(value = "cake_user_nick_name")
    @ApiModelProperty(value = "用户昵称")
    private String cakeUserNickName;

    /**
     * 用户角色ID
     */
    @TableField(value = "cake_role_id")
    @ApiModelProperty(value = "用户角色ID")
    private Integer cakeRoleId;

    /**
     * 用户电话
     */
    @TableField(value = "cake_user_phone")
    @ApiModelProperty(value = "用户电话")
    private String cakeUserPhone;

    /**
     * 用户ip
     */
    @TableField(value = "cake_user_ip")
    @ApiModelProperty(value = "用户ip")
    private String cakeUserIp;

    /**
     * 用户等级
     */
    @TableField(value = "cake_user_level")
    @ApiModelProperty(value = "用户等级")
    private Integer cakeUserLevel;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 用户状态
     */
    @TableField(value = "cake_user_status")
    @ApiModelProperty(value = "用户状态")
    private Integer cakeUserStatus;
}