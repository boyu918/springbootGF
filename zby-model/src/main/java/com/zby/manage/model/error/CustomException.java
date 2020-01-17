package com.zby.manage.model.error;

import com.sun.org.apache.bcel.internal.classfile.Code;
import lombok.Data;

/**
 * @author zby
 * @time 2019/5/21 16:27
 */
@Data
public class CustomException extends Exception{
    private int code = 444;

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
