package com.lxl.cakeadmin.exception.handler;

import com.lxl.cakeadmin.exception.NoPermitException;
import com.lxl.cakeadmin.exception.RoleLevelException;
import com.lxl.cakeadmin.exception.UserLevelException;
import com.lxl.cakeadmin.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ProjectName: cake-admin
 * @Package: com.lxl.cakeadmin.exception.handler
 * @ClassName: CustomExceptionHandler
 * @Author: 63283
 * @Description:
 * @Date: 2023/12/13 17:31
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RoleLevelException.class)
    public Result<Object> roleLevelException(RoleLevelException e) {
        return Result.fail().message(e.getMessage());
    }

    @ExceptionHandler(UserLevelException.class)
    public Result<Object> userLevelException(UserLevelException e) {
        return Result.fail().message(e.getMessage());
    }

    @ExceptionHandler(NoPermitException.class)
    public Result<Object> noPermitException(NoPermitException e){
        return Result.fail().message(e.getMessage());
    }

}
