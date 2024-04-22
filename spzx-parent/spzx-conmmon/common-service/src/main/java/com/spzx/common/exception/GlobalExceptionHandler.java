package com.spzx.common.exception;

import com.spzx.model.vo.common.Result;
import com.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: GlobalExceptionHandler
 * Package: com.spzx.common.exception
 * Description:
 * Author: zhj
 * Create: 2024/4/12 - 9:47
 * Version: v1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public Result error(GlobalException g){
        System.out.println(11);
        return Result.build(null,g.getResultCodeEnum());
    }
}
