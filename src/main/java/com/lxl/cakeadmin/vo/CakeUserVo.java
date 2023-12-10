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
    Long cakeUserId;
    String cakeUserUsername;
    String cakeUserAvatar;
    String cakeUserNickName;
    List<GrantedAuthority> cakeUserAuthority;
    String cakeUserPhone;
    String cakeUserIp;
    Integer cakeUserLevel;
    Integer cakeUserStatus;
    Date createTime;
}
