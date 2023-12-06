package com.lxl.cakeadmin.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.aop
 * @ClassName: Own
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/6 9:10
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Own {

}
