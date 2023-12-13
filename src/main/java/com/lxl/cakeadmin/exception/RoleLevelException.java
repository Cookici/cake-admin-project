package com.lxl.cakeadmin.exception;

import org.springframework.stereotype.Component;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.exception
 * @ClassName: RoleLevelException
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/13 17:30
 */
public class RoleLevelException extends Exception {

    public RoleLevelException(String message) {
        super(message);
    }
}
