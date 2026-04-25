package com.mdkj.exception;

import com.mdkj.result.ResultCode;
import lombok.Getter;

/** 自定义异常 */
@Getter
public class ServiceException extends RuntimeException {

    private ResultCode resultCode = null;

    public ServiceException(String coderMessage) {
        super(coderMessage);
        this.resultCode = resultCode;
    }
}