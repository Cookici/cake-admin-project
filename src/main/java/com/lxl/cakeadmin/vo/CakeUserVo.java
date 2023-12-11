package com.lxl.cakeadmin.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.vo
 * @ClassName: CakeUserVo
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/6 17:02
 */
@Data
public class CakeUserVo {
    private Long cakeUserId;
    private String cakeUserUsername;
    private String cakeUserAvatar;
    private  String cakeUserNickName;
    private  List<GrantedAuthority> cakeUserAuthority;
    private  String cakeUserPhone;
    private String cakeUserIp;
    private Integer cakeUserLevel;
    private  Integer cakeUserStatus;
    private  Date createTime;
}
