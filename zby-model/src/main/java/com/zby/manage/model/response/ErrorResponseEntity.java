package com.zby.manage.model.response;

import lombok.Data;

/**
 * @author zby
 * @time 2019/5/21 16:23
 */
@Data
public class ErrorResponseEntity {
    private int code;
    private String msg;

    public ErrorResponseEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
