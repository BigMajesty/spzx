package com.spzx.common.exception;

import com.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * ClassName: GlobalException
 * Package: com.spzx.common.exception
 * Description:
 * Author: zhj
 * Create: 2024/4/12 - 9:56
 * Version: v1.0
 */
@Data
public class GlobalException extends RuntimeException{
    private int code;
    private String message;
    private ResultCodeEnum resultCodeEnum;
    public GlobalException(ResultCodeEnum resultCodeEnum){
        this.resultCodeEnum=resultCodeEnum;
        this.code=resultCodeEnum.getCode();
        this.message=resultCodeEnum.getMessage();

    }
}
